package com.example.moviestv.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.moviestv.data.db.MoviesDao
import com.example.moviestv.data.db.MyDatabase
import com.example.moviestv.data.db.TVShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideDatabase(app: Application): MyDatabase {
        return Room.databaseBuilder(app.applicationContext, MyDatabase::class.java, "movies_and_tv")
            .build()
    }

    @Provides
    fun provideMoviesDao(myDatabase: MyDatabase): MoviesDao {
        return myDatabase.getMoviesDao()
    }

    @Provides
    fun provideTVShowsDao(myDatabase: MyDatabase): TVShowDao {
        return myDatabase.tvShowsDao()
    }

}