package com.example.moviestv.domain.use_cases.movie

import com.example.moviestv.data.repository.list_types.MovieListType
import com.example.moviestv.domain.repository.MovieRepository

class GetMoviesListUseCase(private val repository: MovieRepository) {
    fun execute(movieListType:MovieListType) = repository.getMoviesList(movieListType)
}