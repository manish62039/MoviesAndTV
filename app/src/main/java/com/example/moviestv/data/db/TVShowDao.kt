package com.example.moviestv.data.db

import androidx.room.*
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow

@Dao
interface TVShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTVShowsList(list: List<TVShow>)

    @Query("DELETE FROM TVShows_Table")
    suspend fun clearAllTVShows()

    @Query("SELECT * FROM TVShows_Table")
    fun getAllTVShows(): List<TVShow>

}