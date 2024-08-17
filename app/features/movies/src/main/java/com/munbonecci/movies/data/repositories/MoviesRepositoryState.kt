package com.munbonecci.movies.data.repositories

import com.munbonecci.movies.data.models.MoviesResponse

sealed class MoviesRepositoryState {
    data object Loading : MoviesRepositoryState()
    data class Success(val response: MoviesResponse) : MoviesRepositoryState()
    data class Error(val message: String) : MoviesRepositoryState()
}