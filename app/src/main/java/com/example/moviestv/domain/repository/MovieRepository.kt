package com.example.moviestv.domain.repository

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.movie.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
//    fun getLatestMovies(): Flow<List<Movie>>
//    fun updateLatestMovies(): Flow<List<Movie>>

//    fun getNowPlayingMovies(): Flow<List<Movie>>
//    fun updateNowPlayingMovies(): Flow<List<Movie>>
//
//    fun getPopularMovies(): Flow<List<Movie>>
//    fun updatePopularMovies(): Flow<List<Movie>>
//
//    fun getTopRatedMovies(): Flow<List<Movie>>
//    fun updateTopRatedMovies(): Flow<List<Movie>>
//
//    fun getUpcomingMovies(): Flow<List<Movie>>
//    fun updateUpcomingMovies(): Flow<List<Movie>>

    fun getMoviesList(listType: String): Flow<List<Movie>>
    fun updateMoviesList(listType: String): Flow<List<Movie>>
}