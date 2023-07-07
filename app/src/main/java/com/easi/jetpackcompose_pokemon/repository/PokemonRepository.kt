package com.easi.jetpackcompose_pokemon.repository

import com.easi.jetpackcompose_pokemon.api.Api
import com.easi.jetpackcompose_pokemon.model.responses.Pokemon
import com.easi.jetpackcompose_pokemon.model.responses.PokemonList
import com.easi.jetpackcompose_pokemon.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(private val api: Api) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit = limit, offset = offset)
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage!!)
        }
        return Resource.Success(data = response)
    }

    suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(name = name)
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage!!)
        }
        return Resource.Success(data = response)
    }
}
