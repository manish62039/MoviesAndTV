package com.example.moviestv.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.moviestv.R
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.data.model.tv_show.TVShow
import com.example.moviestv.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        val movie = intent.extras?.get("movie") as? Movie
        val tvShow = intent.extras?.get("tv_show") as? TVShow

        if (movie != null) {
            Log.i("MovieTAG", "onCreate: DataReceived: $movie")

            //Loading Banner
            try {
                val imgUrl = "https://image.tmdb.org/t/p/w500/" + movie.posterPath

                Glide.with(binding.imgMoviePoster.context)
                    .load(imgUrl)
                    .placeholder(R.color.placeholder_bg)
                    .into(binding.imgMoviePoster)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            binding.txtTitle.text = movie.title
            binding.txtReleaseDate.text = movie.releaseDate
            val voteAverage = "${movie.voteAverage} / 10"
            binding.txtVoteAverage.text = voteAverage
            binding.txtVoteCount.text = movie.voteCount.toString()
            binding.txtOverview.text = movie.overview

        } else if (tvShow != null) {
            Log.i("TVShowTAG", "onCreate: DataReceived: $tvShow")

            //Loading Banner
            try {
                val imgUrl = "https://image.tmdb.org/t/p/w500/" + tvShow.posterPath

                Glide.with(binding.imgMoviePoster.context)
                    .load(imgUrl)
                    .placeholder(R.color.placeholder_bg)
                    .into(binding.imgMoviePoster)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            binding.txtTitle.text = tvShow.name
            binding.txtReleaseDate.text = tvShow.firstAirDate
            binding.txtVoteAverage.text = tvShow.voteAverage.toString()
            binding.txtVoteCount.text = tvShow.voteCount.toString()
            binding.txtOverview.text = tvShow.overview

        }

    }
}