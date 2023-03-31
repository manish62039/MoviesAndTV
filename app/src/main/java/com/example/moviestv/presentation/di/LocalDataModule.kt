package com.example.moviestv.presentation.di

import com.example.moviestv.data.db.MoviesDao
import com.example.moviestv.data.db.TVShowDao
import com.example.moviestv.data.repository.datasource.movie.MovieLocalDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsLocalDataSource
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieLocalDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    fun provideMovieLocalDatasource(moviesDao: MoviesDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(moviesDao)
    }

    @Provides
    fun provideTVShowLocalDatasource(tvShowDao: TVShowDao): TVShowsLocalDataSource {
        return TVShowsLocalDataSourceImpl(tvShowDao)
    }
}