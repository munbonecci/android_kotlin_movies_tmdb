package com.munbonecci.favorites.presentation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.munbonecci.movies.data.api.ApiConstants
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.presentation.viewmodel.SaveMovieViewModel

@Composable
fun FavoritesScreen(
    viewModel: SaveMovieViewModel = viewModel(),
    paddingValues: PaddingValues,
    onOptionPressed: (Movie) -> Unit
) {
    viewModel.getAllSavedMovies()
    val uiState by viewModel.saveMovieState.collectAsState()
    val movies = uiState.movies ?: emptyList()

    Column(modifier = Modifier.padding(paddingValues)) {
        LazyStaggeredGridSnippet(items = movies, onOptionPressed = {
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