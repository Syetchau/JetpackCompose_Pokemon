package com.easi.jetpackcompose_pokemon.viewmodel

import androidx.lifecycle.ViewModel
import com.easi.jetpackcompose_pokemon.model.responses.Pokemon
import com.easi.jetpackcompose_pokemon.repository.PokemonRepository
import com.easi.jetpackcompose_pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

    suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        return repository.getPokemonInfo(name = name)
    }
}