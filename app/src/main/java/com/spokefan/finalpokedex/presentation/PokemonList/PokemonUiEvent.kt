package com.spokefan.finalpokedex.presentation.PokemonList

interface PokemonUiEvent {
    data class SearchQueryChanged(val searchQuery: String): PokemonUiEvent
    data class PokemonTypeIdChanged(val typeId: String): PokemonUiEvent
}