package com.example.moviestv.domain.use_cases.tv_shows

import com.example.moviestv.domain.repository.TvShowRepository

class GetLatestTvShowUseCase(private val tvShowRepository: TvShowRepository) {
    fun execute() = tvShowRepository.getLatestTvShow()
}