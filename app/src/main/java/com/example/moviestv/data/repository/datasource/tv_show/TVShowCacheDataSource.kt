package com.example.moviestv.data.repository.datasource.tv_show

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.data.list_types.TVShowListType

interface TVShowCacheDataSource {
    fun getTvShowsList(tvShowListType: TVShowListType): List<TVShow>?
    fun saveTVShowsList(tvShowListType: TVShowListType, tvShowsList: List<TVShow>)
}