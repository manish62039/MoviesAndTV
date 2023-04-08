package com.example.moviestv.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase
import com.example.moviestv.presentation.data.MovieData
import com.example.moviestv.presentation.data.TVShowsData

class MainViewModel(
    private val app: Application,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val updateMoviesListUseCase: UpdateMoviesListUseCase,
    private val getTVShowsUseCase: GetTVShowUseCase,
    private val updateTVShowsUseCase: UpdateTVShowUseCase
) : AndroidViewModel(app) {
    fun getMoviesList(movieListType: MovieListType) = liveData {
        try {
            getMoviesListUseCase.execute(movieListType).collect {
                emit(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<Movie>())
        }
    }

    fun getTVShowsList(tvShowListType: TVShowListType) = liveData {
        try {
            getTVShowsUseCase.execute(tvShowListType).collect {
                emit(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<TVShow>())
        }
    }

    fun updateMoviesList(movieListType: MovieListType) = liveData {
        try {
            updateMoviesListUseCase.execute(movieListType).collect {
                emit(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<Movie>())
        }
    }

    fun updateTVShowsList(tvShowListType: TVShowListType) = liveData {
        try {
            updateTVShowsUseCase.execute(tvShowListType).collect {
                emit(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<TVShow>())
        }
    }
}