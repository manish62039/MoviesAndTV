package com.example.moviestv.domain.use_cases.movie

import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.domain.repository.MovieRepository

class UpdateMoviesListUseCase(private val repository: MovieRepository) {
    fun execute(movieListType: MovieListType) = repository.updateMoviesList(movieListType)
}