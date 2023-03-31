package com.example.moviestv.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.moviestv.BuildConfig
import com.example.moviestv.R
import com.example.moviestv.data.api.TMDBService
import com.example.moviestv.data.db.MyDatabase
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.data.repository.MovieRepositoryImpl
import com.example.moviestv.data.repository.TVShowRepositoryImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieLocalDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.movie.MovieWebDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowCacheDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowsLocalDataSourceImpl
import com.example.moviestv.data.repository.datasourceImpl.tv_show.TVShowsWebDataSourceImpl
import com.example.moviestv.databinding.ActivityMainBinding
import com.example.moviestv.domain.use_cases.movie.GetMoviesListUseCase
import com.example.moviestv.domain.use_cases.movie.UpdateMoviesListUseCase
import com.example.moviestv.domain.use_cases.tv_shows.GetTVShowUseCase
import com.example.moviestv.domain.use_cases.tv_shows.UpdateTVShowUseCase
import com.example.moviestv.presentation.fragments.MovieFragment
import com.example.moviestv.presentation.fragments.TVShowFragment
import com.example.moviestv.presentation.viewmodels.MainViewModel
import com.example.moviestv.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedFragment = -1
    private var fragments = arrayOfNulls<Fragment>(2)
    private lateinit var factory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val database =
            Room.databaseBuilder(applicationContext, MyDatabase::class.java, "movies_and_tv")
                .build()

        val moviesDao = database.getMoviesDao()
        val tvShowsDao = database.tvShowsDao()

        val tmdbService = retrofit.create(TMDBService::class.java)

//        val cacheDataSource = MovieCacheDataSourceImpl()
//        val localDataSource = MovieLocalDataSourceImpl(moviesDao)
//        val webDataSource = MovieWebDataSourceImpl(tmdbService)

        val tvShowCacheDataSource = TVShowCacheDataSourceImpl()
        val tvShowsLocalDataSource = TVShowsLocalDataSourceImpl(tvShowsDao)
        val tvShowsWebDataSource = TVShowsWebDataSourceImpl(tmdbService)

        val movieCacheDataSource = MovieCacheDataSourceImpl()
        val movieLocalDataSource = MovieLocalDataSourceImpl(moviesDao)
        val movieWebDataSource = MovieWebDataSourceImpl(tmdbService)

        val movieRepository =
            MovieRepositoryImpl(movieCacheDataSource, movieLocalDataSource, movieWebDataSource)
        val getMoviesListUseCase = GetMoviesListUseCase(movieRepository)
        val updateMoviesListUseCase = UpdateMoviesListUseCase(movieRepository)

        val tvShowRepository = TVShowRepositoryImpl(
            tvShowCacheDataSource,
            tvShowsLocalDataSource,
            tvShowsWebDataSource
        )
        val getTVShowsUseCase = GetTVShowUseCase(tvShowRepository)
        val updateTvShowUseCase = UpdateTVShowUseCase(tvShowRepository)

        Log.i("TVShowTAG", "onCreate: Staring...")

/*//        CoroutineScope(IO).launch {
//            val listType = TVShowListType.TOP_RATED
//
//            Log.i("TVShowTAG", "ListType: ${listType.name}")
//
//            //Testing
//            getTVShowsUseCase.execute(listType).collect {
//                Log.i("TVShowTAG", "${listType}: $it")
//            }
//
//            delay(3000)
//
//            //Testing
//            getTVShowsUseCase.execute(listType).collect {
//                Log.i("TVShowTAG", "${listType}: $it")
//            }
//
//            delay(3000)
//
//            //Testing
//            updateTvShowUseCase.execute(listType).collect {
//                Log.i("TVShowTAG", "${listType}: $it")
//            }
//
//        }*/

        //ViewModel Factory
        factory = ViewModelFactory(
            application, getMoviesListUseCase, updateMoviesListUseCase, getTVShowsUseCase,
            updateTvShowUseCase
        )

        //ViewModel
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        //Testing livedata
        viewModel.updateTVShowsList(TVShowListType.TOP_RATED).observe(this@MainActivity) {
            Log.i("MovieTAG", "${it.tvShowListType}: ${it.list}")
        }

        //Fragments
        binding.moviesMenu.setOnClickListener {
            selectFragment(0)
        }
        binding.tvShowsMenuMenu.setOnClickListener {
            selectFragment(1)
        }
        selectFragment(0)
    }

    private fun selectFragment(index: Int) {
        if (index == selectedFragment)
            return

        val fr = supportFragmentManager.beginTransaction()

        var fragment = fragments[index]
        if (fragment == null) {
            fragment = if (index == 0)
                MovieFragment()
            else
                TVShowFragment()
        }

        val startTime = System.currentTimeMillis()
        //Change UI
        if (selectedFragment != -1) {
            if (index == 0) {
                binding.moviesMenu.animate().alpha(1f).duration = 300
                binding.tvShowsMenuMenu.animate().alpha(0.5f).duration = 10
            } else {
                binding.moviesMenu.animate().alpha(0.5f).duration = 100
                binding.tvShowsMenuMenu.animate().alpha(1f).duration = 300
            }
        }

        fr.replace(R.id.fragment_container_view, fragment).commit()
        fragments[index] = fragment
        selectedFragment = index
    }

}