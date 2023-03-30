package com.example.moviestv.data.db

import androidx.room.*
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow

@Dao
interface TVShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTVShows(list: List<TVShow>)

    @Query("SELECT * FROM tv_shows_table WHERE list_type = :list_type")
    suspend fun getTVShows(list_type: String): List<TVShow>

    @Query("DELETE FROM tv_shows_table WHERE list_type = :list_type")
    suspend fun clearTVShows(list_type: String)
}