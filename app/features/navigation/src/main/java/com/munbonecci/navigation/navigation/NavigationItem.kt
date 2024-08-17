package com.munbonecci.navigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    data object Popular : NavigationItem("popular", Icons.Default.Star, "Popular")
    data object NowPlaying : NavigationItem("playing", Icons.Default.PlayArrow, "Now Playing")
    data object Favorites : NavigationItem("favorites", Icons.Default.Favorite, "Favorites")
    data object Settings : NavigationItem("settings", Icons.Default.Settings, "Settings")
    data object MovieDetails : NavigationItem("movieDetails", Icons.Default.Person, "Movie Details")
}