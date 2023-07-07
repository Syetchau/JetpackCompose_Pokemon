package com.easi.jetpackcompose_pokemon.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire") val omegaRubyAlphaSapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y") val xy: XY
)