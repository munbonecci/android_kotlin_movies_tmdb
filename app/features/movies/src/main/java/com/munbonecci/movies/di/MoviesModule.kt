package com.munbonecci.movies.di

import com.munbonecci.movies.domain.usecases.GetMostPopularMoviesUseCase
import com.munbonecci.movies.domain.usecases.GetMostPopularMoviesUseCaseImpl
import com.munbonecci.movies.domain.usecases.GetNowPlayingMoviesUseCase
import com.munbonecci.movies.domain.usecases.GetNowPlayingMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MoviesModule {
    @Binds
    abstract fun bindGetMostPopularMoviesUseCase(
        implementation: GetMostPopularMoviesUseCaseImpl
    ): GetMostPopularMoviesUseCase

    @Binds
    abstract fun bindGetNowPlayingMoviesUseCase(
        implementation: GetNowPlayingMoviesUseCaseImpl
    ): GetNowPlayingMoviesUseCase
}