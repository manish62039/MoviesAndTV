package com.example.moviestv.presentation

import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.model.movie.Movie

data class MovieData(
    val movieListType: MovieListType,
    val list: List<Movie>
)