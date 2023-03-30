package com.example.moviestv.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow

@Database(
    entities = [Movie::class],
    version = 1,
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}