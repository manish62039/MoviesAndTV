package com.example.moviestv.data.db

import androidx.room.*
import com.example.moviestv.data.model.movie.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(list: List<Movie>)

    @Query("SELECT * FROM movies_table WHERE list_type = :list_type")
    suspend fun getMovies(list_type: String): List<Movie>

    @Query("DELETE FROM movies_table WHERE list_type = :list_type")
    suspend fun clearMovies(list_type: String)
}