package com.example.moviestv.data.repository.datasourceImpl

import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.repository.datasource.MovieCacheDataSource
import com.example.moviestv.data.repository.list_types.MovieListType

class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var moviesMap = HashMap<String, MutableList<Movie>?>()

    override fun getMovies(listType: MovieListType): List<Movie>? {
        return moviesMap[listType.TYPE]
    }

    override fun saveMovies(listType: MovieListType, movieList: List<Movie>) {
        var list = moviesMap[listType.TYPE]

        if (list == null)
            list = mutableListOf()
        else
            list.clear()

        list.addAll(movieList)
    }

}