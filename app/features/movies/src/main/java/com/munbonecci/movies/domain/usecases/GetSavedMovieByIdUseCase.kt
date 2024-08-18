package com.munbonecci.movies.domain.usecases
import com.munbonecci.movies.db.dao.MovieDao
import com.munbonecci.movies.db.entity.MovieEntity
import com.munbonecci.movies.domain.models.Movie
import javax.inject.Inject

class GetSavedMovieByIdUseCase @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend operator fun invoke(id: Int): Movie {
        val movie = movieDao.getMovieById(id).toMovie()
        return movie
    }

    private fun MovieEntity.toMovie(): Movie {
        return Movie(
            id = this.id,
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
            genreIds = this.genreIds,
        )
    }
}