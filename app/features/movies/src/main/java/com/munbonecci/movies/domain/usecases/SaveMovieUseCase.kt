package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.db.dao.MovieDao
import com.munbonecci.movies.db.entity.MovieEntity
import com.munbonecci.movies.domain.models.Movie
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend operator fun invoke(movie: Movie): Boolean {
        movieDao.insert(movie.toMovieEntity())
        return true
    }

    private fun Movie.toMovieEntity(): MovieEntity {
        val movie = MovieEntity(id = this.id ?: 0,
            title = this.title,
            overview = this.overview,
            posterPath = this.posterPath,
            backdropPath = this.backdropPath,
            releaseDate = this.releaseDate,
            originalLanguage = this.originalLanguage,
            originalTitle = this.originalTitle,
            popularity = this.popularity,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount,
            adult = this.adult,
            genreIds = this.genreIds
        )
        return movie
    }
}