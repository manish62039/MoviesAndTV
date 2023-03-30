package com.example.moviestv.data.repository.datasource.tv_show

import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow

interface TVShowsLocalDataSource {
    suspend fun saveTVShowsList(listType: TVShowListType, list: List<TVShow>)
    suspend fun clearTVShowsList(listType: TVShowListType)
    suspend fun getTVShowsList(listType: TVShowListType): List<TVShow>
}