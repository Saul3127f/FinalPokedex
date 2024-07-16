package com.spokefan.finalpokedex.presentation.PokemonList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    onColorChange: (Color) -> Unit,
    searchQuery: String,
    onItemClick: (String, List<String>) -> Unit,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    val typeId by viewModel.pokemonTypeId.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = searchQuery) {
        viewModel.onEvent(PokemonUiEvent.SearchQueryChanged(searchQuery))
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (typeId.isNotEmpty()) {
            typeFilterList.find { it.id == typeId }?.let {
                TypeBarSection(
                    type = it,
                    onColorChange = { color ->
                        if (navController.currentDestination?.route != Screen.PokemonDetails.route) {
                            onColorChange(color)
                        }
                    }
                )
            }
        }

        if (state.loadError.isNotEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                RetrySection(error = state.loadError) {
                    if (typeId.isNotEmpty()) {
                        viewModel.getPokemonListByType(typeId)
                    } else {
                        viewModel.loadPokemonPaginated()
                    }
                }
            }
        } else {
            PokemonListSection(
                modifier = if (typeId.isNotEmpty()) {
                    Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topEnd = 19.dp, topStart = 19.dp)
                        )
                        .padding(top = 35.dp)
                } else
                    Modifier.padding(top = 5.dp),
                state = state,
                onItemClick = { id, color ->
                    onColorChange(color)
                    onItemClick(id, if (typeId.isNotEmpty()) state.data.map { it.id } else emptyList())
                },
                loadPokemonPaginated = {
                    if (typeId.isNotEmpty()) {
                        viewModel.getPokemonListByType(typeId)
                    } else {
                        viewModel.loadPokemonPaginated()
                    }
                }
            )
        }
    }
}