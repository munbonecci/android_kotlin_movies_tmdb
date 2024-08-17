package com.munbonecci.navigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    data object Popular : NavigationItem("popular", Icons.Default.Home, "Popular")
    data object NowPlaying : NavigationItem("playing", Icons.Default.Menu, "Now Playing")
    data object Favorites : NavigationItem("favorites", Icons.Default.Favorite, "Favorites")
    data object Settings : NavigationItem("settings", Icons.Default.Person, "Settings")
}