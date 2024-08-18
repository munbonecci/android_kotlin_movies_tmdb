package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.db.dao.MovieDao
import com.munbonecci.movies.db.entity.MovieEntity
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend operator fun invoke(movie: MovieEntity): Boolean {
        movieDao.insert(movie)
        return true
    }
}