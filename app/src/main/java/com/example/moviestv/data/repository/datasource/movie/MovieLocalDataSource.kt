package com.example.moviestv.data.repository.datasource.movie

import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.model.movie.Movie

interface MovieLocalDataSource {
    suspend fun saveMoviesList(movieListType: MovieListType, list: List<Movie>)
    suspend fun clearMoviesList(movieListType: MovieListType)
    suspend fun getMoviesList(movieListType: MovieListType): List<Movie>
}