package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.data.repositories.MoviesRepository
import com.munbonecci.movies.data.repositories.MoviesRepositoryState
import javax.inject.Inject

class GetNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetNowPlayingMoviesUseCase {

    override suspend operator fun invoke( body: MoviesRequest): MoviesRepositoryState {
        return moviesRepository.getNowPlayingMovies(body)
    }
}