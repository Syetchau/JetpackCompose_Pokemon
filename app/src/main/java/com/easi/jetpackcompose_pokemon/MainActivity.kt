package com.easi.jetpackcompose_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.easi.jetpackcompose_pokemon.constants.Constants.KW_DOMINANT_COLOR
import com.easi.jetpackcompose_pokemon.constants.Constants.KW_POKEMON_NAME
import com.easi.jetpackcompose_pokemon.constants.Constants.ROUTE_POKEMON_DETAIL
import com.easi.jetpackcompose_pokemon.constants.Constants.ROUTE_POKEMON_LIST
import com.easi.jetpackcompose_pokemon.ui.theme.JetpackCompose_PokemonTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_PokemonTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ROUTE_POKEMON_LIST
                ) {
                    composable(route = ROUTE_POKEMON_LIST) {

                    }
                    composable(route = "$ROUTE_POKEMON_DETAIL/{$KW_DOMINANT_COLOR}/{$KW_POKEMON_NAME}/", arguments = listOf(
                        navArgument(KW_DOMINANT_COLOR) {
                            type = NavType.IntType
                        },
                        navArgument(KW_POKEMON_NAME) {
                            type = NavType.StringType
                        })
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(KW_DOMINANT_COLOR)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString(KW_POKEMON_NAME)
                        }
                    }
                }
            }
        }
    }
}
