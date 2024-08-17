package com.munbonecci.movies.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.munbonecci.movies.data.api.ApiConstants
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.presentation.MoviesUiState.Error
import com.munbonecci.movies.presentation.MoviesUiState.Loading
import com.munbonecci.movies.presentation.MoviesUiState.Success
import com.munbonecci.movies.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieDetailsScreen(paddingValues: PaddingValues, viewModel: MoviesViewModel, movieId: Int?) {
    val uiState by viewModel.uiStateForMovies.collectAsState()
    var movie = Movie()
    when (uiState) {
        is Loading -> {}
        is Success -> {
            movie = (uiState as Success).movies.toMutableList().find {
                it.id == movieId
            }!!
        }

        is Error -> (uiState as Error).message
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = movie.title ?: "", style = MaterialTheme.typography.h5)
            Text(text = "Géneros: ${movie.genreIds.joinToString(", ")}")
            Text(text = "Sinopsis: ${movie.overview}")
            Text(text = "Popularidad: ${movie.popularity}")
            Text(text = "Fecha de estreno: ${movie.releaseDate}")
            Text(text = "Idiomas: ${movie.originalLanguage}")
            Text(text = "Puntuación: ${movie.voteAverage}")
        }
    }
}