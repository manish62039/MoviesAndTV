package com.example.moviestv.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviestv.BuildConfig
import com.example.moviestv.R
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.repository.MovieRepositoryImpl
import com.example.moviestv.data.repository.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.MovieWebDataSourceImpl
import com.example.moviestv.data.repository.list_types.MovieListType
import com.example.moviestv.domain.repository.MovieRepository
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
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

        CoroutineScope(IO).launch {
            val listType = MovieListType.LATEST
            getMovieUseCase.execute(listType).collect {
                Log.i("MovieTAG", "${listType.TYPE}: $it")
            }
        }

    }
}