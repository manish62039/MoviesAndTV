package com.example.moviestv.domain.repository

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getTopRatedTvShows(): Flow<List<TVShow>>
    fun updateTopRatedTvShows(): Flow<List<TVShow>>

    fun getPopularTvShow(): Flow<List<TVShow>>
    fun updatePopularTvShow(): Flow<List<TVShow>>

    fun getLatestTvShow(): Flow<List<TVShow>>
    fun updateLatestTvShow(): Flow<List<TVShow>>
}