package com.easi.jetpackcompose_pokemon.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.easi.jetpackcompose_pokemon.model.responses.PokedexListEntry
import com.easi.jetpackcompose_pokemon.repository.PokemonRepository
import com.easi.jetpackcompose_pokemon.utils.Constants.IMAGE_BASE_URL
import com.easi.jetpackcompose_pokemon.utils.Constants.PAGE_SIZE
import com.easi.jetpackcompose_pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReachOfPage = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearching = true
    var isSearchingState = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val list = if (isSearching)  pokemonList.value else cachedPokemonList

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                pokemonList.value = cachedPokemonList
                isSearchingState.value = false
                isSearching = true
                return@launch
            }
            val result = list.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) || it.number.toString() == query.trim()
            }

            if (isSearching) {
                cachedPokemonList = pokemonList.value
                isSearching = false
            }
            pokemonList.value = result
            isSearchingState.value = true
        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getPokemonList(limit = PAGE_SIZE, offset = (currentPage * PAGE_SIZE))

            when(result) {
                is Resource.Success -> {
                    endReachOfPage.value = currentPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { _, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val imageUrl = "$IMAGE_BASE_URL${number}.png"
                        PokedexListEntry(
                            pokemonName = entry.name.capitalize(Locale.ROOT),
                            imageUrl = imageUrl,
                            number = number.toInt()
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> { }
            }
        }
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate{ palette ->
            palette?.dominantSwatch?.rgb?.let {
                onFinish(Color(it))
            }
        }
    }
}