package com.easi.jetpackcompose_pokemon.view

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
import com.easi.jetpackcompose_pokemon.utils.Constants
import com.easi.jetpackcompose_pokemon.ui.theme.JetpackCompose_PokemonTheme
import com.easi.jetpackcompose_pokemon.utils.Constants.KW_DOMINANT_COLOR
import com.easi.jetpackcompose_pokemon.utils.Constants.KW_POKEMON_NAME
import com.easi.jetpackcompose_pokemon.utils.Constants.ROUTE_POKEMON_DETAIL
import com.easi.jetpackcompose_pokemon.view.pokemonDetail.PokemonDetailScreen
import com.easi.jetpackcompose_pokemon.view.pokemonList.PokemonListScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_PokemonTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Constants.ROUTE_POKEMON_LIST
                ) {
                    composable(route = Constants.ROUTE_POKEMON_LIST) {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        route = "${ROUTE_POKEMON_DETAIL}/{${KW_DOMINANT_COLOR}}/{${KW_POKEMON_NAME}}",
                        arguments = listOf(
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
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}