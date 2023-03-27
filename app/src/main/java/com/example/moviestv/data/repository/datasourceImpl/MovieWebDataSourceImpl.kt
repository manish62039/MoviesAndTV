package com.example.moviestv.data.repository.datasourceImpl

import android.util.Log
import com.example.moviestv.BuildConfig
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.repository.datasource.MovieWebDataSource
import com.example.moviestv.data.repository.list_types.MovieListType
import retrofit2.Response

class MovieWebDataSourceImpl(private val tmdbService: TMDBService) : MovieWebDataSource {
    override suspend fun getMoviesList(listType: String): Response<MovieList> {
        val k = BuildConfig.API_KEY

        when (listType) {
            MovieListType.LATEST.TYPE ->
                return tmdbService.getLatestMovies(k)

            MovieListType.NOW_PLAYING.TYPE ->
                return tmdbService.getNowPlayingMovies(k)

            MovieListType.TOP_RATED.TYPE ->
                return tmdbService.getTopRatedMovies(k)

            MovieListType.UPCOMING.TYPE ->
                return tmdbService.getUpcomingMovies(k)
        }

        //Invalid list Type
        if (listType != MovieListType.POPULAR.TYPE) {
            Log.i("MovieTAG", "getLatestMoviesList: Invalid List Type")
        }

        //Default
        return tmdbService.getPopularMovies(k)
    }

}