package com.munbonecci.movies.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.munbonecci.movies.data.api.ApiConstants
import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.presentation.MoviesUiState
import com.munbonecci.movies.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieListScreen() {
    val viewModel: MoviesViewModel = viewModel()
    viewModel.getMostPopularMovies(MoviesRequest())
    viewModel.getNowPlayingMovies(MoviesRequest())
    val uiState by viewModel.uiStateForGetMostPopularMovies.collectAsState()
    val uiStateForNowPlayingMovies by viewModel.uiStateForNowPlayingMovies.collectAsState()
    var lista = remember { mutableListOf<Movie>() }
    var showGrid by remember { mutableStateOf(false) }

    when (uiState) {
        is MoviesUiState.Loading -> {}
        is MoviesUiState.Success -> {lista = (uiState as MoviesUiState.Success).movies.toMutableList()
        }
        is MoviesUiState.Error -> (uiState as MoviesUiState.Error).message
    }

    Column {
        Button(
            onClick = { showGrid = !showGrid },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(text = if (showGrid) "Ver lista" else "Ver cuadrícula")
        }

        if (showGrid) {
            LazyStaggeredGridSnippet(items = lista)
        } else {
            ListItem(items = lista )
        }
    }
}

@Composable
fun LazyStaggeredGridSnippet(items: List<Movie>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(items) { movie ->
                AsyncImage(
                    model = "${ApiConstants.POSTER_URL}${movie.posterPath}",
                    contentScale = ContentScale.Crop,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ListItem(items: List<Movie>) {
    LazyColumn {
        items(items) { item ->
            MyCard(item)
        }
    }
}

@Composable
fun MyCard(movie: Movie) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                model = "${ApiConstants.POSTER_URL}${movie.posterPath}",
                contentDescription = movie.title,
                modifier = Modifier.size(150.dp)
            )
            Column(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp)
            ) {
                Text(text = movie.title ?: "", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = movie.releaseDate ?: "")
                Text(text = movie.voteAverage.toString())
            }
        }
    }
}