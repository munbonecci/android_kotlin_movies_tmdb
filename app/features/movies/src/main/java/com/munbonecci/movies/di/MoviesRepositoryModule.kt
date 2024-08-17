package com.munbonecci.movies.di

import com.munbonecci.movies.data.api.MovieApi
import com.munbonecci.movies.data.repositories.MoviesRepository
import com.munbonecci.movies.data.repositories.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MoviesRepositoryModule {
    @Provides
    fun provideMoviesRepository(movieApi: MovieApi): MoviesRepository {
        return MoviesRepositoryImpl(movieApi)
    }
}