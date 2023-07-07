package com.easi.jetpackcompose_pokemon.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white") val blackWhite: BlackWhite
)