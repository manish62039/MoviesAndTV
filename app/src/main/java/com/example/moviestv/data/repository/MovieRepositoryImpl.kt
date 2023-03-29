package com.example.moviestv.data.repository

import android.util.Log
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.repository.datasource.MovieCacheDataSource
import com.example.moviestv.data.repository.datasource.MovieWebDataSource
import com.example.moviestv.data.repository.datasource.TVShowCacheDataSource
import com.example.moviestv.data.repository.list_types.MovieListType
import com.example.moviestv.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieCacheDataSource: MovieCacheDataSource,
    private val movieWebDataSource: MovieWebDataSource
) : MovieRepository {

    override fun getMoviesList(listType: MovieListType): Flow<List<Movie>> {
        return flow {
            val list = getMoviesFromCache(listType)
            emit(list)
        }
    }

    override fun updateMoviesList(listType: MovieListType): Flow<List<Movie>> {
        return flow {
            val list = getMoviesFromWeb(listType)
            movieCacheDataSource.saveMovies(listType, list)
            emit(list)
        }
    }

    private suspend fun getMoviesFromCache(listType: MovieListType): List<Movie> {
        var list = movieCacheDataSource.getMovies(listType)
        if (list.isNullOrEmpty()) {
            list = getMoviesFromWeb(listType)
            movieCacheDataSource.saveMovies(listType, list)
        }
        return list
    }

    private suspend fun getMoviesFromWeb(listType: MovieListType): List<Movie> {
        val result = movieWebDataSource.getMoviesList(listType)
        val body = result.body()
        val list = mutableListOf<Movie>()
        if (body != null) {
            val movies = body.movies
            if (movies == null) {
                Log.i("MovieTAG", "Error: Movies list is null")
            } else if (movies.isEmpty()) {
                Log.i("MovieTAG", "Error: Movies list is empty")
            } else {
                Log.i("MovieTAG", "getMoviesFromWeb: Adding movie list size: ${movies.size}")
                list.addAll(body.movies)
            }
        }
        return list
    }

}