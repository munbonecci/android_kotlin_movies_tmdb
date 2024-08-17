package com.munbonecci.movies.data.repositories

import android.annotation.SuppressLint
import com.munbonecci.movies.data.api.MovieApi
import com.munbonecci.movies.data.models.MoviesRequest
import com.munbonecci.movies.data.models.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar

class MoviesRepositoryImpl(private val movieService: MovieApi) : MoviesRepository {
    override suspend fun getMostPopularMovies(body: MoviesRequest): MoviesRepositoryState {
        return withContext(Dispatchers.IO) {
            val response = movieService.getMostPopularMovies(
                apiKey = body.apiKey,
                language = body.language,
                page = body.page
            )
            getMoviesRepositoryState(response)
        }
    }

    override suspend fun getNowPlayingMovies(body: MoviesRequest): MoviesRepositoryState {
        return withContext(Dispatchers.IO) {
            val response = movieService.getNowPlayingMovies(
                apiKey = body.apiKey,
                language = body.language,
                page = body.page,
                minDate = getYesterdayAndTodayDate().first,
                maxDate = getYesterdayAndTodayDate().second
            )
            getMoviesRepositoryState(response)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getYesterdayAndTodayDate(): Pair<String, String> {
        val calendar = Calendar.getInstance()
        val today = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday = calendar.time

        val formatter = SimpleDateFormat("yyyy-MM-dd")

        return Pair(formatter.format(yesterday), formatter.format(today))
    }

    private fun getMoviesRepositoryState(response: Response<MoviesResponse>): MoviesRepositoryState {
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    MoviesRepositoryState.Success(it)
                } ?: MoviesRepositoryState.Error(response.message())
            } else  MoviesRepositoryState.Error(UNKNOWN_ERROR)
        } catch (e: Exception) {
            MoviesRepositoryState.Error(e.message ?: UNKNOWN_ERROR)
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "Unknown Error"
    }
}