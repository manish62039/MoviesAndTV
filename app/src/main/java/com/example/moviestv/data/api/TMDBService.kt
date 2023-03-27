package com.example.moviestv.data.api

import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.model.tv_show.TVShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    //Movies
    @GET("movie/latest")
    suspend fun getLatestMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): Response<MovieList>

    //TV Shows
    @GET("tv/latest")
    suspend fun getLatestTVShows(@Query("api_key") apiKey: String): Response<TVShowList>

    @GET("tv/popular")
    suspend fun getPopularTVShows(@Query("api_key") apiKey: String): Response<TVShowList>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(@Query("api_key") apiKey: String): Response<TVShowList>

}