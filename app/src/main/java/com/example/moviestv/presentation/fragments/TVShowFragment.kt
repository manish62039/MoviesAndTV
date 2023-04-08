package com.example.moviestv.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestv.presentation.activities.DetailsActivity
import com.example.moviestv.R
import com.example.moviestv.data.list_types.TVShowListType
import com.example.moviestv.databinding.FragmentTVShowBinding
import com.example.moviestv.presentation.activities.MainActivity
import com.example.moviestv.presentation.adapter.TVShowsAdapter
import com.example.moviestv.presentation.utils.Utilities
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
    private var lastUpdateTime = 0L
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

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

        getAllLists()
        binding.refreshBtn.setOnClickListener {
            updateAllLists()
        }
    }

    private fun getAllLists() {
        if (Utilities.isNetworkAvailable(mContext)) {
            queuesList.clear()
            getList(TVShowListType.POPULAR, binding.RVPopular)
            getList(TVShowListType.TOP_RATED, binding.RVTopRated)
        } else {
            selectNoInterNetFragment()
        }
    }

    private fun updateAllLists() {
        if (Utilities.isNetworkAvailable(mContext)) {
            val time = System.currentTimeMillis() - lastUpdateTime
            if (time > 5000) {
                queuesList.clear()
                testingQueue = false

                binding.contentLayout.visibility = RecyclerView.INVISIBLE
                binding.pbLayout.visibility = RecyclerView.VISIBLE

                updateList(TVShowListType.POPULAR)
                updateList(TVShowListType.TOP_RATED)

                lastUpdateTime = System.currentTimeMillis()
            } else {
                Toast.makeText(context, "Already Updated", Toast.LENGTH_SHORT).show()
            }
        } else {
            selectNoInterNetFragment()
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
            delay(100)

            if (queuesList.isEmpty()) {
                Log.i("MovieTAG", "checkIfCompletes: Completed!")
                binding.contentLayout.visibility = RecyclerView.VISIBLE
                binding.pbLayout.visibility = RecyclerView.INVISIBLE
            }

            testingQueue = false
        }
    }

    private fun selectNoInterNetFragment() {
        val fragmentActivity = mContext as FragmentActivity
        val fr = fragmentActivity.supportFragmentManager.beginTransaction()

        val noInternetFragment = NoInternetFragment.newInstance(1)
        fr.replace(R.id.fragment_container_view, noInternetFragment)
            .addToBackStack(null).commit()
    }

}