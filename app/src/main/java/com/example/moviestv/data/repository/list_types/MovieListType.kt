package com.example.moviestv.data.repository.list_types

enum class MovieListType(val TYPE: String) {
    LATEST("latest_movie"),
    NOW_PLAYING("now_playing_movies"),
    POPULAR("popular_movies"),
    TOP_RATED("top_rated_movies"),
    UPCOMING("upcoming_movies")
}