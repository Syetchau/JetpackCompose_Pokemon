package com.easi.jetpackcompose_pokemon.model.responses

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)