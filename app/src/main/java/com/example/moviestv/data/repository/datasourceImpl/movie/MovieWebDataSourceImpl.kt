package com.example.moviestv.data.repository.datasourceImpl.movie

import android.util.Log
import com.example.moviestv.BuildConfig
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.repository.datasource.movie.MovieWebDataSource
import com.example.moviestv.data.repository.list_types.MovieListType
import retrofit2.Response

class MovieWebDataSourceImpl(private val tmdbService: TMDBService) : MovieWebDataSource {
    override suspend fun getMoviesList(listType: MovieListType): Response<MovieList> {
        val k = BuildConfig.API_KEY

        when (listType) {
            MovieListType.LATEST ->
                return tmdbService.getLatestMovies(k)

            MovieListType.NOW_PLAYING ->
                return tmdbService.getNowPlayingMovies(k)

            MovieListType.TOP_RATED ->
                return tmdbService.getTopRatedMovies(k)

            MovieListType.UPCOMING ->
                return tmdbService.getUpcomingMovies(k)

            else -> {
                //Invalid list Type
                if (listType != MovieListType.POPULAR) {
                    Log.i("MovieTAG", "getLatestMoviesList: Invalid List Type")
                }

                //Default
                return tmdbService.getPopularMovies(k)
            }
        }


    }

}