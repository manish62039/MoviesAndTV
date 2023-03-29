package com.example.moviestv.data.repository.datasource

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.list_types.MovieListType

interface MovieCacheDataSource {
    fun getMovies(listType: MovieListType): List<Movie>?
    fun saveMovies(listType: MovieListType, movieList: List<Movie>)
}