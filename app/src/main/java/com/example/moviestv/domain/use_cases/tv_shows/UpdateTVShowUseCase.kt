package com.example.moviestv.domain.use_cases.tv_shows

import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.domain.repository.TvShowRepository

class UpdateTVShowUseCase(private val repository: TvShowRepository) {
    fun execute(tvShowListType: TVShowListType) = repository.updateTvShowsList(tvShowListType)
}