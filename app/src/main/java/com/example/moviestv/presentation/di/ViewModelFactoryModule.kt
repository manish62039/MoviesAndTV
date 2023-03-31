package com.example.moviestv.presentation.di

import android.app.Application
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase
import com.example.moviestv.presentation.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {
    @Provides
    fun provideMainViewModelFactory(
        app: Application,
        getMoviesListUseCase: GetMoviesListUseCase,
        updateMoviesListUseCase: UpdateMoviesListUseCase,
        getTVShowsUseCase: GetTVShowUseCase,
        updateTVShowsUseCase: UpdateTVShowUseCase
    ): ViewModelFactory {
        return ViewModelFactory(
            app,
            getMoviesListUseCase,
            updateMoviesListUseCase,
            getTVShowsUseCase,
            updateTVShowsUseCase
        )
    }
}