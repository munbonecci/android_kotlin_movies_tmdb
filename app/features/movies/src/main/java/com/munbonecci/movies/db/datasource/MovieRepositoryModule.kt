package com.munbonecci.movies.db.datasource

import com.munbonecci.movies.db.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(movieDao: MovieDao): MovieDataSource {
        return RoomMovieDataSource(movieDao)
    }
}