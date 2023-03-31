package com.example.moviestv.presentation.di

import com.example.moviestv.domain.repository.MovieRepository
import com.example.moviestv.domain.repository.TvShowRepository
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetMoviesListUseCase(movieRepository: MovieRepository): GetMoviesListUseCase {
        return GetMoviesListUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMoviesListUseCase(movieRepository: MovieRepository): UpdateMoviesListUseCase {
        return UpdateMoviesListUseCase(movieRepository)
    }

    @Provides
    fun provideGetTVShowUseCase(tvShowRepository: TvShowRepository): GetTVShowUseCase {
        return GetTVShowUseCase(tvShowRepository)
    }

    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTVShowUseCase {
        return UpdateTVShowUseCase(tvShowRepository)
    }
}