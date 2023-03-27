package com.example.moviestv.data.repository.datasource

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.movie.MovieList
import retrofit2.Response

interface MovieWebDataSource {
    suspend fun getMoviesList(listType:String): Response<MovieList>
}