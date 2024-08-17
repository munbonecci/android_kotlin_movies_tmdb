package com.munbonecci.movies.data.repositories

import com.munbonecci.movies.data.models.MoviesRequest

interface MoviesRepository {
    suspend fun getMostPopularMovies(body: MoviesRequest): MoviesRepositoryState
    suspend fun getNowPlayingMovies(body: MoviesRequest): MoviesRepositoryState
}