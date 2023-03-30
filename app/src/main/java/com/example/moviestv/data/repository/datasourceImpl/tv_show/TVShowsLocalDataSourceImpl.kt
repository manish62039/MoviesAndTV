package com.example.moviestv.data.repository.datasourceImpl.tv_show

import com.example.moviestv.data.db.MoviesDao
import com.example.moviestv.data.db.TVShowDao
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.data.repository.datasource.movie.MovieLocalDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsLocalDataSource
import java.util.*

class TVShowsLocalDataSourceImpl(private val tvShowDao: TVShowDao) : TVShowsLocalDataSource {
    override suspend fun saveTVShowsList(listType: TVShowListType, list: List<TVShow>) {
        addListType(listType, list)
        tvShowDao.saveTVShows(list)
    }

    override suspend fun clearTVShowsList(listType: TVShowListType) {
        tvShowDao.clearTVShows(listType.name)
    }

    override suspend fun getTVShowsList(listType: TVShowListType): List<TVShow> {
        return tvShowDao.getTVShows(listType.name)
    }

    private fun addListType(tvShowListType: TVShowListType, list: List<TVShow>) {

        if (list.isNotEmpty()) {
            if (list[0].listType.isNullOrEmpty()) {
                val listType = tvShowListType.name
                list.forEach { it.listType = listType }
            }
        }

    }
}