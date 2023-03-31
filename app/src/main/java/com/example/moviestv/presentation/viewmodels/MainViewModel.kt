package com.example.moviestv.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase
import com.example.moviestv.presentation.MovieData
import com.example.moviestv.presentation.TVShowsData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val app: Application,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val updateMoviesListUseCase: UpdateMoviesListUseCase,
    private val getTVShowsUseCase: GetTVShowUseCase,
    private val updateTVShowsUseCase: UpdateTVShowUseCase
) : AndroidViewModel(app) {
    fun getMoviesList(movieListType: MovieListType) = liveData {
        getMoviesListUseCase.execute(movieListType).collect {
            val data = MovieData(movieListType, it)
            emit(data)
        }
    }

    fun getTVShowsList(tvShowListType: TVShowListType) = liveData {
        getTVShowsUseCase.execute(tvShowListType).collect {
            val data = TVShowsData(tvShowListType, it)
            emit(data)
        }
    }

    fun updateMoviesList(movieListType: MovieListType) = liveData {
        updateMoviesListUseCase.execute(movieListType).collect {
            val data = MovieData(movieListType, it)
            emit(data)
        }
    }

    fun updateTVShowsList(tvShowListType: TVShowListType) = liveData {
        updateTVShowsUseCase.execute(tvShowListType).collect {
            val data = TVShowsData(tvShowListType, it)
            emit(data)
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

}