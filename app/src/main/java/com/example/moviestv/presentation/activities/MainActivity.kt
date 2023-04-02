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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedFragment = -1

    @Inject
    lateinit var factory: ViewModelFactory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModel
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        //Testing livedata
//        viewModel.getMoviesList(MovieListType.POPULAR).observe(this@MainActivity) {
//            Log.i("MovieTAG", "${it.movieListType}: ${it.list}")
//        }

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

        Log.i("MovieTAG", "Selecting Fragment")

        val fragment = if (index == 0)
            MovieFragment()
        else
            TVShowFragment()

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
        selectedFragment = index
    }

}