package com.example.moviestv.data.repository.datasource.tv_show

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.data.repository.list_types.TVShowListType

interface TVShowCacheDataSource {
//    fun getLatestTVShows(): List<TVShow>?
//    fun saveLatestTVShows(tvShowsList: List<TVShow>)
//
//    fun getPopularTVShows(): List<TVShow>?
//    fun savePopularTVShows(tvShowsList: List<TVShow>)
//
//    fun getTopRatedTVShows(): List<TVShow>?
//    fun saveTopRatedTVShows(tvShowsList: List<TVShow>)

    fun getTvShowsList(tvShowListType: TVShowListType): List<TVShow>?
    fun saveTVShowsList(tvShowListType: TVShowListType, list: List<TVShow>)
}