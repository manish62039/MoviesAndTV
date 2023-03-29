package com.example.moviestv.domain.repository

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.repository.list_types.MovieListType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMoviesList(listType: MovieListType): Flow<List<Movie>>
    fun updateMoviesList(listType: MovieListType): Flow<List<Movie>>
}