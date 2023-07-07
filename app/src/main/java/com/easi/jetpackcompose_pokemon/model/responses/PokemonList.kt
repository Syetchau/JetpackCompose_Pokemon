package com.easi.jetpackcompose_pokemon.model.responses

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)