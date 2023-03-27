package com.example.moviestv.data.db

import androidx.room.*
import com.example.moviestv.data.model.movie.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLatestMovies(list: List<Movie>)

    @Query("DELETE FROM movies_table")
    suspend fun clearAllMovies()

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): List<Movie>

}