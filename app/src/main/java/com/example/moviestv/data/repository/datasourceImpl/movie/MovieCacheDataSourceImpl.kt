package com.example.moviestv.data.repository.datasourceImpl.movie

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.datasource.movie.MovieCacheDataSource
import com.example.moviestv.data.repository.list_types.MovieListType


class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var moviesMap = HashMap<MovieListType, ArrayList<Movie>>()

    override fun getMovies(listType: MovieListType): List<Movie>? {
        return moviesMap[listType]
    }

    override fun saveMovies(listType: MovieListType, movieList: List<Movie>) {
        if (moviesMap.containsKey(listType)) {
            val list = moviesMap[listType]
            list?.clear()
            list?.addAll(movieList)
        } else {
            moviesMap[listType] = movieList as ArrayList<Movie>
        }
    }

}