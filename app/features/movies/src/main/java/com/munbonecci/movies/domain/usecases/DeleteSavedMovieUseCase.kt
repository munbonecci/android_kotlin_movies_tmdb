package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.db.dao.MovieDao
import com.munbonecci.movies.domain.models.Movie
import javax.inject.Inject

class DeleteSavedMovieUseCase @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend operator fun invoke(movie: Movie): Boolean {
        movieDao.delete(movie.id ?: 0)
        return true
    }
}