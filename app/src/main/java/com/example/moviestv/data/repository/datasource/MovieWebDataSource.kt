package com.example.moviestv.data.repository.datasource

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.repository.list_types.MovieListType
import retrofit2.Response

interface MovieWebDataSource {
    suspend fun getMoviesList(listType:MovieListType): Response<MovieList>
}