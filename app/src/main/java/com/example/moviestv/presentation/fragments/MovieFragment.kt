package com.example.moviestv.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.moviestv.presentation.activities.DetailsActivity
import com.example.moviestv.R
import com.example.moviestv.data.list_types.MovieListType
import com.example.moviestv.databinding.FragmentMovieBinding
import com.example.moviestv.presentation.activities.MainActivity
import com.example.moviestv.presentation.adapter.MoviesAdapter
import com.example.moviestv.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MainViewModel
    private var map = HashMap<MovieListType, MoviesAdapter>()
    private var queuesList = arrayListOf<MovieListType>()
    private var testingQueue = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        queuesList.clear()
        getList(MovieListType.POPULAR, binding.RVPopular)
        getList(MovieListType.TOP_RATED, binding.RVTopRated)
        getList(MovieListType.NOW_PLAYING, binding.RVNowPlaying)
        getList(MovieListType.UPCOMING, binding.RVUpcoming)

        binding.refreshBtn.setOnClickListener {
            queuesList.clear()
            testingQueue = false

            binding.contentLayout.visibility = INVISIBLE
            binding.pbLayout.visibility = VISIBLE

            updateList(MovieListType.POPULAR)
            updateList(MovieListType.TOP_RATED)
            updateList(MovieListType.NOW_PLAYING)
            updateList(MovieListType.UPCOMING)
        }
    }

    private fun getList(movieListType: MovieListType, recyclerView: RecyclerView) {
        queuesList.add(movieListType)

        viewModel.getMoviesList(movieListType).observe(viewLifecycleOwner) {
            Log.i("MovieTAG", "getList: $movieListType -> $it")

            var adapter = map[movieListType]
            if (adapter == null) {
                adapter = MoviesAdapter()
                adapter.setClickListener { movie ->
                    Log.i("MovieTAG", "getList: OnClicked: $movie")
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }
            }
            adapter.differ.submitList(it)

            recyclerView.layoutManager =
                LinearLayoutManager(context, HORIZONTAL, false)
            recyclerView.adapter = adapter

            map[movieListType] = adapter

            queuesList.remove(movieListType)
            checkIfCompletes()
        }
    }

    private fun updateList(movieListType: MovieListType) {
        queuesList.add(movieListType)

        viewModel.updateMoviesList(movieListType).observe(viewLifecycleOwner) {
            Log.i("MovieTAG", "getList: $movieListType -> $it")

            val adapter = map[movieListType]

            adapter?.differ?.submitList(it)

            map[movieListType] = adapter!!

            queuesList.remove(movieListType)
            checkIfCompletes()

        }
    }

    private fun checkIfCompletes() {
        if (testingQueue)
            return

        testingQueue = true
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)

            if (queuesList.isEmpty()) {
                Log.i("MovieTAG", "checkIfCompletes: Completed!")
                binding.contentLayout.visibility = VISIBLE
                binding.pbLayout.visibility = INVISIBLE
            }

            testingQueue = false
        }
    }

}