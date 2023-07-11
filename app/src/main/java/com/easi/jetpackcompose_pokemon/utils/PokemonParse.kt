package com.easi.jetpackcompose_pokemon.utils

import androidx.compose.ui.graphics.Color
import com.easi.jetpackcompose_pokemon.model.responses.Stat
import com.easi.jetpackcompose_pokemon.model.responses.Type
import com.easi.jetpackcompose_pokemon.ui.theme.AtkColor
import com.easi.jetpackcompose_pokemon.ui.theme.DefColor
import com.easi.jetpackcompose_pokemon.ui.theme.HPColor
import com.easi.jetpackcompose_pokemon.ui.theme.SpAtkColor
import com.easi.jetpackcompose_pokemon.ui.theme.SpDefColor
import com.easi.jetpackcompose_pokemon.ui.theme.SpdColor
import com.easi.jetpackcompose_pokemon.ui.theme.TypeBug
import com.easi.jetpackcompose_pokemon.ui.theme.TypeDark
import com.easi.jetpackcompose_pokemon.ui.theme.TypeDragon
import com.easi.jetpackcompose_pokemon.ui.theme.TypeElectric
import com.easi.jetpackcompose_pokemon.ui.theme.TypeFairy
import com.easi.jetpackcompose_pokemon.ui.theme.TypeFighting
import com.easi.jetpackcompose_pokemon.ui.theme.TypeFire
import com.easi.jetpackcompose_pokemon.ui.theme.TypeFlying
import com.easi.jetpackcompose_pokemon.ui.theme.TypeGhost
import com.easi.jetpackcompose_pokemon.ui.theme.TypeGrass
import com.easi.jetpackcompose_pokemon.ui.theme.TypeGround
import com.easi.jetpackcompose_pokemon.ui.theme.TypeIce
import com.easi.jetpackcompose_pokemon.ui.theme.TypeNormal
import com.easi.jetpackcompose_pokemon.ui.theme.TypePoison
import com.easi.jetpackcompose_pokemon.ui.theme.TypePsychic
import com.easi.jetpackcompose_pokemon.ui.theme.TypeRock
import com.easi.jetpackcompose_pokemon.ui.theme.TypeSteel
import com.easi.jetpackcompose_pokemon.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}