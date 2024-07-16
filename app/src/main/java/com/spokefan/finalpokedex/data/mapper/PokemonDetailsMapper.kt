package com.spokefan.finalpokedex.data.mapper

import com.spokefan.finalpokedex.data.remote.responses.PokemonResponse
import com.spokefan.finalpokedex.data.remote.responses.PokemonSpeciesResponse
import com.spokefan.finalpokedex.domain.model.PokemonDetails
import com.spokefan.finalpokedex.util.getPokemonImage

object PokemonDetailsMapper {
    fun buildFrom(
        pokemonResponse: PokemonResponse,
        pokemonSpeciesResponse: PokemonSpeciesResponse
    ): PokemonDetails {
        return PokemonDetails(
            id = pokemonResponse.id,
            name = pokemonResponse.name,
            imageUrl = getPokemonImage(pokemonResponse.id.toString()),
            category = pokemonSpeciesResponse.getGenusByLanguage("en") ?: "Not Found",
            height = pokemonResponse.height,
            weight = pokemonResponse.weight,
            type = pokemonResponse.types.map { it.type.name },
            flavorText = pokemonSpeciesResponse.getFlavorText("en","red") ?: pokemonSpeciesResponse.getRandomFlavorText("en") ?: ""
        )
    }
}