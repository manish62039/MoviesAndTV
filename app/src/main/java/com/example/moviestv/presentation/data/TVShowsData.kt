package com.example.moviestv.presentation.data

import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.model.tv_show.TVShow

data class TVShowsData(
    val tvShowListType: TVShowListType,
    val list: List<TVShow>
)