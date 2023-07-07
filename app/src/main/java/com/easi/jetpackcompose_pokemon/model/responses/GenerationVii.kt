package com.easi.jetpackcompose_pokemon.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
)