package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.data.repositories.MoviesRepository
import com.munbonecci.movies.data.repositories.MoviesRepositoryState
import javax.inject.Inject

class GetMostPopularMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMostPopularMoviesUseCase {

    override suspend operator fun invoke(body: MoviesRequest): MoviesRepositoryState {
        return moviesRepository.getMostPopularMovies(body)
    }
}