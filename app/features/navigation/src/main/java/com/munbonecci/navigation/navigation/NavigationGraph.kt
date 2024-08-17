package com.munbonecci.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.munbonecci.movies.presentation.screen.MovieListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Popular.route) {
        composable(NavigationItem.Popular.route) {
            MovieListScreen()
        }
        composable(NavigationItem.NowPlaying.route) {
            MovieListScreen()
        }
        composable(NavigationItem.Favorites.route) {
            MovieListScreen()
        }
        composable(NavigationItem.Settings.route) {
            MovieListScreen()
        }
    }
}