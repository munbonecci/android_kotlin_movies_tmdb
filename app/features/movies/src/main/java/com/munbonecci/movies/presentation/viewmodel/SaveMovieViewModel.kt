package com.munbonecci.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.movies.db.entity.MovieEntity
import com.munbonecci.movies.domain.models.Movie
import com.munbonecci.movies.domain.usecases.GetAllSavedMoviesUseCase
import com.munbonecci.movies.domain.usecases.SaveMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveMovieViewModel @Inject constructor(
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getAllSavedMoviesUseCase: GetAllSavedMoviesUseCase
) : ViewModel() {

    private val _saveMovieState = MutableLiveData<SaveMovieState>()
    val saveMovieState: LiveData<SaveMovieState> = _saveMovieState

    fun saveMovie(movie: MovieEntity) {
        viewModelScope.launch {
            saveMovieUseCase(movie)
            getAllSavedMovies()
        }
    }

    fun getAllSavedMovies() {
        viewModelScope.launch {
            _saveMovieState.value = SaveMovieState(isLoading = true)
            val movies = getAllSavedMoviesUseCase()
            _saveMovieState.value = SaveMovieState(movies = movies, isLoading = false)
        }
    }
}

data class SaveMovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null,
    val error: String = ""
)