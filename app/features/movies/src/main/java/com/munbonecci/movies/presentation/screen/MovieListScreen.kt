package com.munbonecci.movies.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.munbonecci.movies.R
import com.munbonecci.movies.data.api.ApiConstants
import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.presentation.MoviesUiState
import com.munbonecci.movies.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieListScreen(
    viewModel: MoviesViewModel = viewModel(),
    isMostPopular: Boolean = true,
    paddingValues: PaddingValues,
    onOptionPressed: (Movie) -> Unit
) {

    if (isMostPopular) {
        viewModel.getMostPopularMovies(MoviesRequest())
    } else {
        viewModel.getNowPlayingMovies(MoviesRequest())
    }

    val uiState by viewModel.uiStateForMovies.collectAsState()

    var movies = remember { mutableListOf<Movie>() }
    var showGrid by remember { mutableStateOf(false) }

    when (uiState) {
        is MoviesUiState.Loading -> {}
        is MoviesUiState.Success -> {
            movies = (uiState as MoviesUiState.Success).movies.toMutableList()
        }

        is MoviesUiState.Error -> (uiState as MoviesUiState.Error).message
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.padding(12.dp),
                onClick = { showGrid = !showGrid }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (showGrid) Icons.Default.Menu else Icons.AutoMirrored.Filled.List,
                        contentDescription = stringResource(id = R.string.grid_list_button),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = if (showGrid) stringResource(id = R.string.see_list)
                        else stringResource(id = R.string.see_grid)
                    )
                }
            }
        }

        ShowGridOrListScreen(showGrid, movies, onOptionPressed = {
            onOptionPressed(it)
        } )
    }
}

@Composable
private fun ShowGridOrListScreen(
    showGrid: Boolean,
    movies: List<Movie>,
    onOptionPressed: (Movie) -> Unit
) {
    if (showGrid) {
        LazyStaggeredGridSnippet(items = movies, onOptionPressed = {
            onOptionPressed(it)
        })
    } else {
        ListItem(items = movies, onOptionPressed = {
            onOptionPressed(it)
        })
    }
}

@Composable
fun LazyStaggeredGridSnippet(items: List<Movie>, onOptionPressed: (Movie) -> Unit) {
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
                        .clickable {
                            onOptionPressed(movie)
                        }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ListItem(items: List<Movie>, onOptionPressed: (Movie) -> Unit) {
    LazyColumn {
        items(items) { item ->
            ContainerForListCard(item, onOptionPressed)
        }
    }
}

@Composable
fun ContainerForListCard(movie: Movie, onOptionPressed: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onOptionPressed(movie)
            },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "${ApiConstants.POSTER_URL}${movie.posterPath}",
                contentDescription = movie.title,
                modifier = Modifier.size(150.dp),
                alignment = Alignment.CenterStart
            )
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = movie.title ?: "", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = movie.releaseDate ?: "")
                Text(text = movie.voteAverage.toString())
            }
        }
    }
}