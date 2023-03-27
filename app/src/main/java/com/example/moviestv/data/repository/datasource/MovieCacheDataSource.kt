package com.example.moviestv.data.repository.datasource

import com.example.moviestv.data.model.movie.Movie

interface MovieCacheDataSource {
    fun getMovies(listType: String): List<Movie>?
    fun saveMovies(listType: String, movieList: List<Movie>)
}