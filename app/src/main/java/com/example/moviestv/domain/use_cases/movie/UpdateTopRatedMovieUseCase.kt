package com.example.moviestv.domain.use_cases.movie

import com.example.moviestv.domain.repository.MovieRepository

class UpdateTopRatedMovieUseCase(private val repository: MovieRepository) {
    fun execute() = repository.updateTopRatedMovies()
}