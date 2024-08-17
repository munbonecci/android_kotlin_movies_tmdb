package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.data.repositories.MoviesRepositoryState

fun interface GetMostPopularMoviesUseCase {
    suspend operator fun invoke( body: MoviesRequest): MoviesRepositoryState
}