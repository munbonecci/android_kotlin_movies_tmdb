package com.munbonecci.movies.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.presentation.MoviesUiState
import com.munbonecci.movies.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieListScreen() {
    val viewModel: MoviesViewModel = viewModel()
    viewModel.getMostPopularMovies(MoviesRequest())
    viewModel.getNowPlayingMovies(MoviesRequest())
    val uiState by viewModel.uiStateForGetMostPopularMovies.collectAsState()

    val text: String = when (uiState) {
        is MoviesUiState.Loading -> "Loading"
        is MoviesUiState.Success -> (uiState as MoviesUiState.Success).movies.toString()
        is MoviesUiState.Error -> (uiState as MoviesUiState.Error).message
    }

    Text(text = text)
}