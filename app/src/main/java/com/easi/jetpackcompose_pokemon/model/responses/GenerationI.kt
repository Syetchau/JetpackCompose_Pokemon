package com.easi.jetpackcompose_pokemon.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") val redBlue: RedBlue,
    val yellow: Yellow
)