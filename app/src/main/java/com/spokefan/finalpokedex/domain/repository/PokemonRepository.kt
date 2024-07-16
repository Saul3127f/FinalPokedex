package com.spokefan.finalpokedex.domain.repository

import com.spokefan.finalpokedex.data.remote.responses.PokemonListByTypeResponse
import com.spokefan.finalpokedex.data.remote.responses.PokemonListResponse
import com.spokefan.finalpokedex.domain.model.PokemonDetails
import com.spokefan.finalpokedex.util.Resource

interface PokemonRepository {
    suspend fun getPokemonList(curPage: Int): Resource<PokemonListResponse>

    suspend fun getPokemonListByType(id: String): Resource<PokemonListByTypeResponse>

    suspend fun getPokemonDetails(id: String): Resource<PokemonDetails>

    suspend fun getPokemonTypes(id: String): List<String>
}