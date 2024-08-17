package com.munbonecci.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.data.repositories.MoviesRepositoryState
import com.munbonecci.movies.domain.usecases.GetMostPopularMoviesUseCase
import com.munbonecci.movies.domain.usecases.GetNowPlayingMoviesUseCase
import com.munbonecci.movies.presentation.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMostPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _uiStateForGetMostPopularMovies = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiStateForGetMostPopularMovies: StateFlow<MoviesUiState> = _uiStateForGetMostPopularMovies.asStateFlow()

    private val _uiStateForNowPlayingMovies = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiStateForNowPlayingMovies: StateFlow<MoviesUiState> = _uiStateForNowPlayingMovies.asStateFlow()

    fun getMostPopularMovies(body: MoviesRequest) {
        viewModelScope.launch {
            val response = getMostPopularMoviesUseCase(body)
            val stateForGetMostPopularMovies = getMoviesUIState(response)
            _uiStateForGetMostPopularMovies.update { stateForGetMostPopularMovies }
        }
    }

    fun getNowPlayingMovies(body: MoviesRequest) {
        viewModelScope.launch {
            val response = getNowPlayingMoviesUseCase(body)
            val stateForNowPlayingMovies = getMoviesUIState(response)
            _uiStateForNowPlayingMovies.update { stateForNowPlayingMovies }
        }
    }

    private fun getMoviesUIState(response: MoviesRepositoryState): MoviesUiState = when (response) {
        is MoviesRepositoryState.Success -> {
            MoviesUiState.Success(response.response.results)
        }
        is MoviesRepositoryState.Error -> {
            MoviesUiState.Error(response.message)
        }
        is MoviesRepositoryState.Loading -> MoviesUiState.Loading
    }
}