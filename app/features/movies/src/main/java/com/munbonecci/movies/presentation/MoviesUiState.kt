package com.munbonecci.movies.presentation

import com.munbonecci.movies.domain.models.Movie

sealed class MoviesUiState {
    data object Loading : MoviesUiState()
    data class Success(val movies: List<Movie>) : MoviesUiState()
    data class Error(val message: String) : MoviesUiState()
}