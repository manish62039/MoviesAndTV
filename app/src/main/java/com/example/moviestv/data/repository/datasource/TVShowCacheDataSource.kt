package com.example.moviestv.data.repository.datasource

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow

interface TVShowCacheDataSource {
    fun getLatestTVShows(): List<TVShow>?
    fun saveLatestTVShows(tvShowsList: List<TVShow>)

    fun getPopularTVShows(): List<TVShow>?
    fun savePopularTVShows(tvShowsList: List<TVShow>)

    fun getTopRatedTVShows(): List<TVShow>?
    fun saveTopRatedTVShows(tvShowsList: List<TVShow>)
}