package com.example.moviestv.data.repository.datasourceImpl

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.datasource.MovieCacheDataSource
import com.example.moviestv.data.repository.list_types.MovieListType

class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var moviesMap = HashMap<String, MutableList<Movie>?>()

    override fun getMovies(listType: String): List<Movie>? {
        return moviesMap[listType]
    }

    override fun saveMovies(listType: String, movieList: List<Movie>) {
        var list = moviesMap[listType]

        if (list == null)
            list = mutableListOf()
        else
            list.clear()

        list.addAll(movieList)
    }

}