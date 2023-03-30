package com.example.moviestv.data.repository.datasourceImpl.movie

import com.example.moviestv.data.db.MoviesDao
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.datasource.movie.MovieLocalDataSource
import java.util.*

class MovieLocalDataSourceImpl(private val moviesDao: MoviesDao) : MovieLocalDataSource {
    override suspend fun saveMoviesList(movieListType: MovieListType, list: List<Movie>) {
        addListType(movieListType, list)
        moviesDao.saveMovies(list)
    }

    override suspend fun clearMoviesList(movieListType: MovieListType) {
        moviesDao.clearMovies(movieListType.name)
    }

    override suspend fun getMoviesList(movieListType: MovieListType): List<Movie> {
        return moviesDao.getMovies(movieListType.name)
    }

    private fun addListType(movieListType: MovieListType, list: List<Movie>) {

        if (list.isNotEmpty()) {
            if (list[0].listType.isNullOrEmpty()) {
                val listType = movieListType.name
                list.forEach { it.listType = listType }
            }
        }

    }

}