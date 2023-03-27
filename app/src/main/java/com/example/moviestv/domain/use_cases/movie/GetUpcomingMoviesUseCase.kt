package com.example.moviestv.domain.use_cases.movie

import com.example.moviestv.domain.repository.MovieRepository

class GetUpcomingMoviesUseCase(private val repository: MovieRepository) {
    fun execute() = repository.getUpcomingMovies()
}