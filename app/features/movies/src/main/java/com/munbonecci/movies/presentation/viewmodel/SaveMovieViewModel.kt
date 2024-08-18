package com.munbonecci.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.domain.usecases.DeleteSavedMovieUseCase
import com.munbonecci.movies.domain.usecases.GetAllSavedMoviesUseCase
import com.munbonecci.movies.domain.usecases.GetSavedMovieByIdUseCase
import com.munbonecci.movies.domain.usecases.SaveMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveMovieViewModel @Inject constructor(
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getAllSavedMoviesUseCase: GetAllSavedMoviesUseCase,
    private val deleteSavedMovieUseCase: DeleteSavedMovieUseCase,
    private val getSavedMovieByIdUseCase: GetSavedMovieByIdUseCase
) : ViewModel() {

    private val _saveMovieState = MutableStateFlow(SaveMovieState(isLoading = false))
    val saveMovieState: StateFlow<SaveMovieState> = _saveMovieState.asStateFlow()

    private val _saveMovieByIdState = MutableStateFlow(SaveMovieByIdState(isLoading = false))
    val saveMovieByIdState: StateFlow<SaveMovieByIdState> = _saveMovieByIdState.asStateFlow()

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            saveMovieUseCase(movie)
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            deleteSavedMovieUseCase(movie)
        }
    }

    fun getAllSavedMovies() {
        viewModelScope.launch {
            _saveMovieState.update { SaveMovieState(isLoading = true) }
            val movies = getAllSavedMoviesUseCase()
            _saveMovieState.update { SaveMovieState(movies = movies, isLoading = false) }
        }
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            _saveMovieByIdState.update { SaveMovieByIdState(isLoading = true) }
            val movie = getSavedMovieByIdUseCase(id)
            _saveMovieByIdState.update { SaveMovieByIdState(movie = movie, isLoading = false) }
        }
    }
}

data class SaveMovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null,
    val error: String = ""
)

data class SaveMovieByIdState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String = ""
)