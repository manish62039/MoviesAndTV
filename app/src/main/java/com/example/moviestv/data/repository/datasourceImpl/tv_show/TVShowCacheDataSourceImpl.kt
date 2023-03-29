package com.example.moviestv.data.repository.datasourceImpl.tv_show

import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.data.repository.datasource.tv_show.TVShowCacheDataSource
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShowList

class TVShowCacheDataSourceImpl : TVShowCacheDataSource {
    private var tvShowsMap = HashMap<TVShowListType, ArrayList<TVShow>>()

    override fun getTvShowsList(tvShowListType: TVShowListType): List<TVShow>? {
        return tvShowsMap[tvShowListType]
    }

    override fun saveTVShowsList(tvShowListType: TVShowListType, tvShowsList: List<TVShow>) {
        if (tvShowsMap.containsKey(tvShowListType)) {
            val list = tvShowsMap[tvShowListType]
            list?.clear()
            list?.addAll(tvShowsList)
        } else {
            tvShowsMap[tvShowListType] = tvShowsList as ArrayList<TVShow>
        }
    }
}