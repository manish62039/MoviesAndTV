package com.example.moviestv.domain.repository

import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getTvShowsList(listType: TVShowListType): Flow<List<TVShow>>
    fun updateTvShowsList(listType: TVShowListType): Flow<List<TVShow>>
}