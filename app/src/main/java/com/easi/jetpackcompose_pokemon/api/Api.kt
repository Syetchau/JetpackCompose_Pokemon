package com.easi.jetpackcompose_pokemon.api

import com.easi.jetpackcompose_pokemon.model.responses.Pokemon
import com.easi.jetpackcompose_pokemon.model.responses.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int,
                               @Query("offset") offset: Int): PokemonList


    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): Pokemon
}