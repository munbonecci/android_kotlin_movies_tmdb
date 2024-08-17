package com.munbonecci.movies.domain.repository

import com.munbonecci.movies.db.datasource.MovieDataSource
import com.munbonecci.movies.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource: MovieDataSource) {

    suspend fun insertMovie(movie: MovieEntity) {
        movieDataSource.insertMovie(movie)
    }

    fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDataSource.getAllMovies()
    }

     fun getMovieById(movieId: Int): Flow<MovieEntity?> {
        return movieDataSource.getMovieById(movieId)
    }

     suspend fun insertAll(movies: List<MovieEntity>) {
         movieDataSource.insertAll(movies)
    }

     suspend fun delete(movie: MovieEntity) {
         movieDataSource.delete(movie)
    }

     suspend fun update(movie: MovieEntity) {
         movieDataSource.update(movie)
    }

     suspend fun deleteAll() {
         movieDataSource.deleteAll()
    }
}