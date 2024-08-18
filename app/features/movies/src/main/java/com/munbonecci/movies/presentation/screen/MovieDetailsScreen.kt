package com.munbonecci.movies.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.munbonecci.movies.R
import com.munbonecci.movies.data.api.ApiConstants
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.presentation.MoviesUiState
import com.munbonecci.movies.presentation.MoviesUiState.Error
import com.munbonecci.movies.presentation.MoviesUiState.Loading
import com.munbonecci.movies.presentation.MoviesUiState.Success
import com.munbonecci.movies.presentation.viewmodel.MoviesViewModel
import com.munbonecci.movies.presentation.viewmodel.SaveMovieViewModel

@Composable
fun MovieDetailsScreen(
    paddingValues: PaddingValues,
    viewModel: MoviesViewModel,
    movieId: Int?,
    saveMovieViewModel: SaveMovieViewModel
) {
    val uiState by viewModel.uiStateForMovies.collectAsState()
    var movie: Movie? = Movie()
    movie = movie(uiState, movie, movieId)

    if (movie == null) {
        saveMovieViewModel.getMovieById(movieId ?: 0)
        val savedMovie by saveMovieViewModel.saveMovieByIdState.collectAsState()
        movie = savedMovie.movie
    }

    DetailScreen(paddingValues, movie, saveMovieViewModel)
}

@Composable
private fun DetailScreen(
    paddingValues: PaddingValues,
    movie: Movie?,
    saveMovieViewModel: SaveMovieViewModel
) {
    val isFavorite = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                movie?.let {
                    Poster(movie)

                    IconButton(onClick = {
                        isFavorite.value = !isFavorite.value
                        if (isFavorite.value) saveMovieViewModel.saveMovie(movie)
                        else saveMovieViewModel.deleteMovie(movie)
                    }) {
                        Icon(
                            if (isFavorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(24.dp),
                            tint = if (isFavorite.value) Color.Red else Color.Gray
                        )
                    }

                    DetailInfo(movie)
                }
            }
        }
    }
}

@Composable
private fun movie(
    uiState: MoviesUiState,
    movie: Movie?,
    movieId: Int?
): Movie? {
    var movie1 = movie
    when (uiState) {
        is Loading -> {}
        is Success -> {
            movie1 = uiState.movies.toMutableList().find {
                it.id == movieId
            }
        }

        is Error -> uiState.message
    }
    return movie1
}

@Composable
fun Poster(movie: Movie) {
    Card(
        elevation = 4.dp,
        border = BorderStroke(1.dp, Color.Transparent),
    ) {
        AsyncImage(
            model = "${ApiConstants.POSTER_URL}${movie.posterPath}",
            contentDescription = movie.title,
            modifier = Modifier
                .size(250.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun DetailInfo(movie: Movie) {
    val genre = stringResource(id = R.string.genre)
    val overview = stringResource(id = R.string.overview)
    val popularity = stringResource(id = R.string.popularity)
    val releaseDate = stringResource(id = R.string.release_date)
    val languages = stringResource(id = R.string.languages)
    val voteAverage = stringResource(id = R.string.vote_average)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp),
            text = movie.title ?: "",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Text(
            text = "$overview: ${movie.overview}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "$popularity: ${movie.popularity}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "$releaseDate: ${movie.releaseDate}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "$languages: ${movie.originalLanguage}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "$voteAverage: ${movie.voteAverage}",
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "$genre: ${movie.genreIds}",
            modifier = Modifier.padding(4.dp),
            textAlign = TextAlign.Justify
        )
    }
}