package com.munbonecci.movies.domain.usecases

import com.munbonecci.movies.db.dao.MovieDao
import com.munbonecci.movies.db.entity.MovieEntity
import com.munbonecci.movies.domain.models.Movie
import javax.inject.Inject

class GetAllSavedMoviesUseCase @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend operator fun invoke(): List<Movie> {
        val movies = movieDao.getAllMovies().toMovie()
        return movies
    }

    private fun List<MovieEntity>.toMovie(): List<Movie> {
        val movies = mutableListOf<Movie>()
        this.map { movie ->
            movies.add(
                Movie(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.posterPath,
                    backdropPath = movie.backdropPath,
                    releaseDate = movie.releaseDate,
                    originalLanguage = movie.originalLanguage,
                    originalTitle = movie.originalTitle,
                    popularity = movie.popularity,
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount,
                    adult = movie.adult,
                    genreIds = movie.genreIds,
                )
            )
        }
        return movies
    }
}