package com.munbonecci.movies.db.datasource

import com.munbonecci.movies.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {

    suspend fun insertMovie(movie: MovieEntity)

    fun getAllMovies(): Flow<List<MovieEntity>>

    fun getMovieById(movieId: Int): Flow<MovieEntity?>

    suspend fun insertAll(movies: List<MovieEntity>)

    suspend fun delete(movie: MovieEntity)

    suspend fun update(movie: MovieEntity)

    suspend fun deleteAll()
}