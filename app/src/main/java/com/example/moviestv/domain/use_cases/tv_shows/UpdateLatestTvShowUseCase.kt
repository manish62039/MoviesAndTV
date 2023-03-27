package com.example.moviestv.domain.use_cases.tv_shows

import com.example.moviestv.domain.repository.TvShowRepository

class UpdateLatestTvShowUseCase(private val repository: TvShowRepository) {
    fun execute() = repository.updateLatestTvShow()
}