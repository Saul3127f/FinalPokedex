package com.spokefan.finalpokedex.presentation.pokemonDetails

import com.spokefan.finalpokedex.domain.model.PokemonDetails

data class PokemonDetailsUiState(
    val pokemon: PokemonDetails = PokemonDetails(),
    val loadError: String = "",
    val isLoading: Boolean = false
)
