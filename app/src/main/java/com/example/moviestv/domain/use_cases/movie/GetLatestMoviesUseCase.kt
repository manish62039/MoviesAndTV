package com.example.moviestv.domain.use_cases.movie

import com.example.moviestv.domain.repository.MovieRepository

class GetLatestMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute() = movieRepository.getLatestMovies()

}