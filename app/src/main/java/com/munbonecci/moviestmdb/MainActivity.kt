package com.munbonecci.moviestmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.munbonecci.moviestmdb.ui.theme.MoviesTMDBTheme
import com.munbonecci.navigation.navigation.BottomNavigationBar
import com.munbonecci.navigation.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTMDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationMainScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationMainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
                    },
        content = { paddingValues ->
            NavigationGraph(navController = navController)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesTMDBTheme {
        NavigationMainScreen()
    }
}