package com.example.moviestv.data.repository

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.datasource.MovieCacheDataSource
import com.example.moviestv.data.repository.datasource.MovieWebDataSource
import com.example.moviestv.data.repository.datasource.TVShowCacheDataSource
import com.example.moviestv.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieCacheDataSource: MovieCacheDataSource,
    private val movieWebDataSource: MovieWebDataSource
) : MovieRepository {

    override fun getMoviesList(listType: String): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun updateMoviesList(listType: String): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

}