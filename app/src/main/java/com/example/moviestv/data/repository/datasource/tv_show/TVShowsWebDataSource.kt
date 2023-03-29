package com.example.moviestv.data.repository.datasource.tv_show

import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.movie.MovieList
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.data.model.tv_show.TVShowList
import retrofit2.Response

interface TVShowsWebDataSource {
    suspend fun getTVShowsList(listType: TVShowListType): Response<TVShowList>
}