package com.example.moviestv.presentation.di

import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.repository.datasource.movie.MovieWebDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsWebDataSource
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieWebDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowsWebDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class WebDataModule {
    @Provides
    fun provideMovieWebDataSource(tmdbService: TMDBService): MovieWebDataSource {
        return MovieWebDataSourceImpl(tmdbService)
    }

    @Provides
    fun provideTVShowWebDatasource(tmdbService: TMDBService): TVShowsWebDataSource {
        return TVShowsWebDataSourceImpl(tmdbService)
    }
}