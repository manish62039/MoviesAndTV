package com.example.moviestv.data.repository.datasourceImpl

import com.example.moviestv.BuildConfig
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.model.tv_show.TVShowList
import com.example.moviestv.data.repository.datasource.TVShowsWebDataSource
import retrofit2.Response

class TVShowsWebDataSourceImpl(private val tmdbService: TMDBService) : TVShowsWebDataSource {
    override suspend fun getTopRatedTvShows(): Response<TVShowList> {
        return tmdbService.getTopRatedTVShows(BuildConfig.API_KEY)
    }

    override suspend fun getPopularTvShows(): Response<TVShowList> {
        return tmdbService.getPopularTVShows(BuildConfig.API_KEY)
    }

    override suspend fun getLatestTvShows(): Response<TVShowList> {
        return tmdbService.getLatestTVShows(BuildConfig.API_KEY)
    }
}