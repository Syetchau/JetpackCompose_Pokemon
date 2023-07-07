package com.easi.jetpackcompose_pokemon.model.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)