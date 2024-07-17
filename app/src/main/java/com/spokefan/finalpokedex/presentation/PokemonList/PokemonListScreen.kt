package com.spokefan.finalpokedex.presentation.PokemonList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
@Composable
private fun PokemonListSection(
    modifier: Modifier = Modifier,
    state: PokemonListUiState,
    onItemClick: (String, Color) -> Unit,
    loadPokemonPaginated: () -> Unit = {}
) {
    val listState = rememberLazyGridState()

    if (state.isSearching && state.data.isEmpty()) {
        EmptyPokemonList(modifier)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyVerticalGrid(
                    state = listState,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(17.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    val itemCount = if (state.data.size % 2 == 0) {
                        state.data.size
                    } else {
                        state.data.size + 1
                    }
                    items(count = state.data.size, key = { index -> state.data[index].id }) { item ->
                        if (item >= itemCount - 1 && !state.endReached && !state.isLoading && !state.isDataFiltered) {
                            LaunchedEffect(key1 = true) {
                                loadPokemonPaginated()
                            }
                        }
                        PokemonItem(
                            item = state.data[item],
                            onItemClick = { id, color ->
                                onItemClick(id, color)
                            }
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 60.dp)
                ) {
                    if (state.isLoading && state.data.isEmpty()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(90.dp),
                            color = Color(0xFFE4A121),
                            strokeWidth = 5.dp
                        )
                    }
                }

                this@Column.AnimatedVisibility(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    visible = state.isLoading && state.data.isNotEmpty(),
                    enter = slideInVertically { 200 }.plus(fadeIn(animationSpec = tween(120))),
                    exit = slideOutVertically { 200 }.plus(fadeOut(animationSpec = tween(120))),
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 21.dp)
                            .size(38.dp)
                            .background(color = Color(0xFF3D2A04), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(21.dp),
                            color = Color(0xFFE4A121),
                            strokeWidth = 2.8.dp
                        )
                    }
                }
            }
        }
    }
}