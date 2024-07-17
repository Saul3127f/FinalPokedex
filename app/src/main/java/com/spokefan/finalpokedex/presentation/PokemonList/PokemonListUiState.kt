package com.spokefan.finalpokedex.presentation.PokemonList

import com.spokefan.finalpokedex.domain.model.Pokemon

data class PokemonListUiState(
    val data: List<Pokemon> = listOf(),
    val loadError: String = "",
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val isSearching: Boolean = false,
    val isDataFiltered: Boolean = false,
)