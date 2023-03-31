package com.example.moviestv.presentation.di

import com.example.moviestv.data.repository.MovieRepositoryImpl
import com.example.moviestv.data.repository.TVShowRepositoryImpl
import com.example.moviestv.data.repository.datasource.movie.MovieCacheDataSource
import com.example.moviestv.data.repository.datasource.movie.MovieLocalDataSource
import com.example.moviestv.data.repository.datasource.movie.MovieWebDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowCacheDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsLocalDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsWebDataSource
import com.example.moviestv.domain.repository.MovieRepository
import com.example.moviestv.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMovieRepository(
        movieCacheDataSource: MovieCacheDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieWebDataSource: MovieWebDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieCacheDataSource, movieLocalDataSource, movieWebDataSource)
    }

    @Provides
    fun provideTVShowRepository(
        tvShowCacheDataSource: TVShowCacheDataSource,
        tvShowsLocalDataSource: TVShowsLocalDataSource,
        tvShowsWebDataSource: TVShowsWebDataSource
    ): TvShowRepository {
        return TVShowRepositoryImpl(
            tvShowCacheDataSource,
            tvShowsLocalDataSource,
            tvShowsWebDataSource
        )
    }
}