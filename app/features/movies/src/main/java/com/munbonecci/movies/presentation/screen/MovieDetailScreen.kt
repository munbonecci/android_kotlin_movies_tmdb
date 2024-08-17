package com.munbonecci.movies.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.munbonecci.movies.data.api.ApiConstants
import com.munbonecci.movies.domain.models.Movie

@Composable
fun MovieDetailsScreen(id: String) {

    val movie = Movie()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            AsyncImage(
                model = movie.posterPath,
                contentDescription ="${ApiConstants.POSTER_URL}${movie.posterPath}",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Top)
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = movie.title ?: "", style = MaterialTheme.typography.h5)
                Text(text = "Géneros: ${movie.genreIds.joinToString(", ")}")
                Text(text = "Sinopsis: ${movie.overview}")
                Text(text = "Popularidad: ${movie.popularity}")
                Text(text = "Fecha de estreno: ${movie.releaseDate}")
                Text(text = "Idiomas: ${movie.originalLanguage}")
                Text(text = "Puntuación: ${movie.voteAverage}")
                Text(text = "Estado: ")
            }
        }
    }
}