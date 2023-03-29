package com.example.moviestv.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviestv.BuildConfig
import com.example.moviestv.R
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.repository.MovieRepositoryImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieWebDataSourceImpl
import com.example.moviestv.data.repository.list_types.MovieListType
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tmdbService = retrofit.create(TMDBService::class.java)

        val cacheDataSource = MovieCacheDataSourceImpl()
        val webDataSource = MovieWebDataSourceImpl(tmdbService)

        val repository = MovieRepositoryImpl(cacheDataSource, webDataSource)
        val getMovieUseCase = GetMoviesListUseCase(repository)
        val updateMoviesUseCase = UpdateMoviesListUseCase(repository)

        CoroutineScope(IO).launch {
            val listType = MovieListType.POPULAR

            //Testing API
            getMovieUseCase.execute(listType).collect {
                Log.i("MovieTAG", "${listType}: $it")
            }

            delay(3000)

            //Testing Cache
            getMovieUseCase.execute(listType).collect() {
                Log.i("MovieTAG", "${listType}: $it")
            }
        }

    }
}