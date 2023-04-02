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
import com.example.moviestv.presentation.activities.DetailsActivity
import com.example.moviestv.R
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.databinding.FragmentTVShowBinding
import com.example.moviestv.presentation.activities.MainActivity
import com.example.moviestv.presentation.adapter.TVShowsAdapter
import com.example.moviestv.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TVShowFragment : Fragment() {
    private lateinit var binding: FragmentTVShowBinding
    private lateinit var viewModel: MainViewModel
    private var map = HashMap<TVShowListType, TVShowsAdapter>()
    private var queuesList = arrayListOf<TVShowListType>()
    private var testingQueue = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_t_v_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTVShowBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        queuesList.clear()
        getList(TVShowListType.POPULAR, binding.RVPopular)
        getList(TVShowListType.TOP_RATED, binding.RVTopRated)


        binding.refreshBtn.setOnClickListener {
            queuesList.clear()
            testingQueue = false

            binding.contentLayout.visibility = RecyclerView.INVISIBLE
            binding.pbLayout.visibility = RecyclerView.VISIBLE

            updateList(TVShowListType.POPULAR)
            updateList(TVShowListType.TOP_RATED)
        }
    }

    private fun getList(tvShowListType: TVShowListType, recyclerView: RecyclerView) {
        queuesList.add(tvShowListType)

        viewModel.getTVShowsList(tvShowListType).observe(viewLifecycleOwner) {
            Log.i("TVShowTAG", "getList: $tvShowListType -> $it")

            var adapter = map[tvShowListType]
            if (adapter == null) {
                adapter = TVShowsAdapter()
                adapter.setClickListener { tvShow ->
                    Log.i("TVShowTAG", "getList: OnClicked: $tvShow")
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("tv_show", tvShow)
                    startActivity(intent)
                }
            }
            adapter.differ.submitList(it)

            recyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            recyclerView.adapter = adapter

            map[tvShowListType] = adapter

            queuesList.remove(tvShowListType)
            checkIfCompletes()

        }
    }

    private fun updateList(tvShowListType: TVShowListType) {
        queuesList.add(tvShowListType)

        viewModel.updateTVShowsList(tvShowListType).observe(viewLifecycleOwner) {
            Log.i("TVShowTAG", "getList: $tvShowListType -> $it")

            val adapter = map[tvShowListType]

            adapter?.differ?.submitList(it)

            map[tvShowListType] = adapter!!

            queuesList.remove(tvShowListType)
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
                binding.contentLayout.visibility = RecyclerView.VISIBLE
                binding.pbLayout.visibility = RecyclerView.INVISIBLE
            }

            testingQueue = false
        }
    }

}