package com.example.moviestv.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.moviestv.BuildConfig
import com.example.moviestv.R
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.db.MyDatabase
import com.example.moviestv.data.repository.MovieRepositoryImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieWebDataSourceImpl
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.repository.TVShowRepositoryImpl
import com.example.moviestv.data.repository.datasource.tv_show.TVShowCacheDataSource
import com.example.moviestv.data.repository.datasource.tv_show.TVShowsWebDataSource
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieLocalDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowsLocalDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowsWebDataSourceImpl
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val database =
            Room.databaseBuilder(applicationContext, MyDatabase::class.java, "movies_and_tv")
                .build()

//        val moviesDao = database.getMoviesDao()
        val moviesDao = database.tvShowsDao()

        val tmdbService = retrofit.create(TMDBService::class.java)

//        val cacheDataSource = MovieCacheDataSourceImpl()
//        val localDataSource = MovieLocalDataSourceImpl(moviesDao)
//        val webDataSource = MovieWebDataSourceImpl(tmdbService)

        val cacheDataSource = TVShowCacheDataSourceImpl()
        val localDataSource = TVShowsLocalDataSourceImpl(moviesDao)
        val webDataSource = TVShowsWebDataSourceImpl(tmdbService)

//        val repository = MovieRepositoryImpl(cacheDataSource, localDataSource, webDataSource)
//        val getMoviesListUseCase = GetMoviesListUseCase(repository)

        val repository = TVShowRepositoryImpl(cacheDataSource, localDataSource, webDataSource)
        val getTVShowsUseCase = GetTVShowUseCase(repository)
        val updateTvShowUseCase = UpdateTVShowUseCase(repository)

        Toast.makeText(applicationContext, "Starting...", Toast.LENGTH_SHORT).show()
        Log.i("TVShowTAG", "onCreate: Staring...")

        CoroutineScope(IO).launch {
            val listType = TVShowListType.TOP_RATED

            Log.i("TVShowTAG", "ListType: ${listType.name}")

            //Testing
            getTVShowsUseCase.execute(listType).collect {
                Log.i("TVShowTAG", "${listType}: $it")
            }

            delay(3000)

            //Testing
            getTVShowsUseCase.execute(listType).collect {
                Log.i("TVShowTAG", "${listType}: $it")
            }

            delay(3000)

            //Testing
            updateTvShowUseCase.execute(listType).collect {
                Log.i("TVShowTAG", "${listType}: $it")
            }

        }

    }
}