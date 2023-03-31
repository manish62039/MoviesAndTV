package com.example.moviestv.presentation.di

import com.example.moviestv.data.repository.datasource.movie.MovieCacheDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowCacheDataSource
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {
    @Provides
    fun provideMovieCacheDatasource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Provides
    fun provideTVShowCacheDatasource(): TVShowCacheDataSource {
        return TVShowCacheDataSourceImpl()
    }
}