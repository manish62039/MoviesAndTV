package com.example.moviestv.data.repository

import android.util.Log
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.datasource.movie.MovieCacheDataSource
import com.example.moviestv.data.repository.datasource.movie.MovieWebDataSource
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.data.repository.datasource.tv_show.TVShowCacheDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsLocalDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsWebDataSource
import com.example.moviestv.domain.repository.MovieRepository
import com.example.moviestv.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TVShowRepositoryImpl(
    private val tvShowCacheDataSource: TVShowCacheDataSource,
    private val tvShowsLocalDataSource: TVShowsLocalDataSource,
    private val tvShowsWebDataSource: TVShowsWebDataSource
) : TvShowRepository {

    override fun getTvShowsList(listType: TVShowListType): Flow<List<TVShow>> {
        return flow {
            val list = getTVShowsFromCache(listType)
            emit(list)
        }
    }

    override fun updateTvShowsList(listType: TVShowListType): Flow<List<TVShow>> {
        return flow {
            val list = getTVShowsFromWeb(listType)
            tvShowCacheDataSource.saveTVShowsList(listType, list)
            emit(list)
        }
    }

    private suspend fun getTVShowsFromCache(listType: TVShowListType): List<TVShow> {
        var list = tvShowCacheDataSource.getTvShowsList(listType)
        if (list.isNullOrEmpty()) {
            list = getMoviesListFromRoom(listType)
            tvShowCacheDataSource.saveTVShowsList(listType, list)
        } else {
            Log.i("TVShowTAG", "getTVShowsFromCache: Size: ${list.size}")
        }
        return list
    }

    private suspend fun getMoviesListFromRoom(listType: TVShowListType): List<TVShow> {
        var list = tvShowsLocalDataSource.getTVShowsList(listType)
        if (list.isEmpty()) {
            list = getTVShowsFromWeb(listType)
            tvShowsLocalDataSource.saveTVShowsList(listType, list)
        } else {
            Log.i("TVShowTAG", "getMoviesListFromRoom: Size: ${list.size}")
        }
        return list
    }

    private suspend fun getTVShowsFromWeb(listType: TVShowListType): List<TVShow> {
        val result = tvShowsWebDataSource.getTVShowsList(listType)
        val body = result.body()
        val list = mutableListOf<TVShow>()
        if (body != null) {
            val tvShows = body.TVShows
            if (tvShows != null) {
                list.addAll(tvShows)
            }
        }
        Log.i("TVShowTAG", "getMoviesFromWeb: Size: ${list.size}")
        return list
    }

}