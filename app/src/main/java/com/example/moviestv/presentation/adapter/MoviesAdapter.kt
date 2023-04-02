package com.example.moviestv.presentation.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviestv.R
import com.example.moviestv.data.model.movie.Movie
import com.example.moviestv.databinding.ItemRecyclerViewBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyViewHolder(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            try {
                val imgUrl = "https://image.tmdb.org/t/p/w500/" + movie.posterPath

                Glide.with(binding.imgMoviePoster.context)
                    .load(imgUrl)
                    .placeholder(R.color.placeholder_bg)
                    .into(binding.imgMoviePoster)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            binding.textMovieName.text = movie.title

            binding.root.setOnClickListener {
                onClickListener?.let { it1 -> it1(movie) }
            }

        }
    }

    private var onClickListener: ((Movie) -> Unit)? = null
    fun setClickListener(onClickListener: (Movie) -> Unit) {
        this.onClickListener = onClickListener
    }
}