package com.example.moviestv.data.repository.datasourceImpl.tv_show

import android.util.Log
import com.example.moviestv.BuildConfig
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.model.tv_show.TVShowList
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsWebDataSource
import retrofit2.Response

class TVShowsWebDataSourceImpl(private val tmdbService: TMDBService) : TVShowsWebDataSource {
    override suspend fun getTVShowsList(listType: TVShowListType): Response<TVShowList> {
        val k = BuildConfig.API_KEY

        when (listType) {
            TVShowListType.LATEST ->
                return tmdbService.getLatestTVShows(k)

            TVShowListType.TOP_RATED ->
                return tmdbService.getTopRatedTVShows(k)

            else -> {
                //Invalid list Type
                if (listType != TVShowListType.POPULAR) {
                    Log.i("TVShowTAGTVShowTAG", "getTVShowsList: Invalid List Type")
                }

                //Default
                return tmdbService.getPopularTVShows(k)
            }
        }


    }
}