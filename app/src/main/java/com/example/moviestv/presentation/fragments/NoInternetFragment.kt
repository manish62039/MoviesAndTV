package com.example.moviestv.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.moviestv.R
import com.example.moviestv.presentation.utils.Utilities


class NoInternetFragment : Fragment() {
    private var fromFragmentIndex: Int = 0
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fromFragmentIndex = it.getInt(ARG_FROM_FRAGMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_internet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnRetry = view.findViewById<Button>(R.id.btn_retry)
        btnRetry.setOnClickListener {
            if (Utilities.isNetworkAvailable(mContext)) {
                deSelectNoInterNetFragment()
            } else {
                Toast.makeText(context, "No Internet!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deSelectNoInterNetFragment() {
        val fragmentActivity = mContext as FragmentActivity
        val fr = fragmentActivity.supportFragmentManager.beginTransaction()

        val fragment = if (fromFragmentIndex == 0)
            MovieFragment()
        else
            TVShowFragment()

        fr.replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null).commit()
    }

    companion object {
        private var ARG_FROM_FRAGMENT = "from_fragment_index"

        fun newInstance(fromFragmentIndex: Int) =
            NoInternetFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_FROM_FRAGMENT, fromFragmentIndex)
                }
            }
    }
}