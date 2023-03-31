package com.example.moviestv.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase

class ViewModelFactory(
    private val app: Application,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val updateMoviesListUseCase: UpdateMoviesListUseCase,
    private val getTVShowsUseCase: GetTVShowUseCase,
    private val updateTVShowsUseCase: UpdateTVShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            app,
            getMoviesListUseCase,
            updateMoviesListUseCase,
            getTVShowsUseCase,
            updateTVShowsUseCase
        ) as T
    }
}