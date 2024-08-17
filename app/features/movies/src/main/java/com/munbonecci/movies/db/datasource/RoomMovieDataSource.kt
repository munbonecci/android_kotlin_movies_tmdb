package com.munbonecci.movies.db.datasource

import com.munbonecci.movies.db.dao.MovieDao
import com.munbonecci.movies.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomMovieDataSource @Inject constructor(
    private val movieDao: MovieDao
) : MovieDataSource {

    override suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insert(movie)
    }

    override fun getMovieById(movieId: Int): Flow<MovieEntity?> {
        return movieDao.getMovieById(movieId)
    }

    override suspend fun insertAll(movies: List<MovieEntity>) {
        movieDao.insertAll(movies)
    }

    override suspend fun delete(movie: MovieEntity) {
        movieDao.delete(movie)
    }

    override suspend fun update(movie: MovieEntity) {
        movieDao.update(movie)
    }

    override suspend fun deleteAll() {
        movieDao.deleteAll()
    }

    override fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }
}