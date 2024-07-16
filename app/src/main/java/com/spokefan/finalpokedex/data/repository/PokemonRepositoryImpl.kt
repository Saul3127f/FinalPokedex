package com.spokefan.finalpokedex.data.repository

import com.spokefan.finalpokedex.data.mapper.PokemonDetailsMapper
import com.spokefan.finalpokedex.data.remote.PokeApi
import com.spokefan.finalpokedex.data.remote.responses.PokemonListByTypeResponse
import com.spokefan.finalpokedex.data.remote.responses.PokemonListResponse
import com.spokefan.finalpokedex.data.remote.responses.PokemonResponse
import com.spokefan.finalpokedex.domain.model.PokemonDetails
import com.spokefan.finalpokedex.domain.repository.PokemonRepository
import com.spokefan.finalpokedex.util.Constants.PAGE_SIZE
import com.spokefan.finalpokedex.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PokemonRepositoryImpl @Inject constructor(private val api: PokeApi) : PokemonRepository {
    override suspend fun getPokemonList(curPage: Int): Resource<PokemonListResponse> {
        val response = try {
            api.getPokemonList(PAGE_SIZE,curPage*PAGE_SIZE)
        }catch(e: Exception){
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonListByType(id: String): Resource<PokemonListByTypeResponse> {
        val response = try {
            api.getPokemonListByType(id)
        }catch(e: Exception){
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonDetails(id: String): Resource<PokemonDetails> = coroutineScope {
        val pokemonResponse = async {
            api.getPokemonDetails(id)
        }.await()

        val pokemonSpeciesResponse = async {
            val pokemonId = pokemonResponse.species.getId()
            api.getPokemonSpecies(pokemonId)
        }.await()

        return@coroutineScope try {
            val response = PokemonDetailsMapper.buildFrom(
                pokemonResponse = pokemonResponse,
                pokemonSpeciesResponse = pokemonSpeciesResponse
            )
            Resource.Success(response)
        } catch(e: Exception) {
            Resource.Error("An unknown error occurred.")
        }
    }

    override suspend fun getPokemonTypes(id: String): List<String> {
        return api.getPokemonDetails(id).types.map {
            it.type.name
        }
    }
}