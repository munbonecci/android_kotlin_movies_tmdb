package com.munbonecci.navigation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.munbonecci.movies.presentation.screen.MovieDetailsScreen
import com.munbonecci.movies.presentation.screen.MovieListScreen
import com.munbonecci.movies.presentation.viewmodel.MoviesViewModel

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    val viewModel: MoviesViewModel = viewModel()
    NavHost(navController, startDestination = NavigationItem.Popular.route) {

        composable(NavigationItem.Popular.route) {
            MovieListScreen(viewModel,true, paddingValues, onOptionPressed = {
                navController.navigate("${NavigationItem.MovieDetails.route}/${it.id}")
            })
        }
        composable(NavigationItem.NowPlaying.route) {
            MovieListScreen(viewModel, false, paddingValues, onOptionPressed = {
                navController.navigate(NavigationItem.MovieDetails.route)
            })
        }
        composable(NavigationItem.Favorites.route) {
            MovieScreen()
        }
        composable(NavigationItem.Settings.route) {
            MovieScreen()
        }
        composable("${NavigationItem.MovieDetails.route}/{id}",  arguments = listOf(
            navArgument("id") { type = NavType.IntType },
        )) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("id", 0)
            MovieDetailsScreen(paddingValues, viewModel, movieId)
        }
    }
}

@Composable
fun MovieScreen() {
    Text(text = "hola")
}