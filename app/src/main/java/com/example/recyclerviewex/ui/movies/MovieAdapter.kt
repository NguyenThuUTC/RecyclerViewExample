package com.example.recyclerviewex.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.databinding.ItemFeaturedBinding
import com.example.recyclerviewex.databinding.ItemMovieBinding

class MovieAdapter(val onMovieClick: (MovieItem) -> Unit) : ListAdapter<MovieUIModel, RecyclerView.ViewHolder>(DiffCallback()) {

    //Featured -> movie đầu trong list
    //Movies

    companion object {
        const val  TYPE_FEATURED = 0
        const val TYPE_MOVIE = 1
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when(item) {
            is MovieUIModel.Movie -> TYPE_MOVIE
            is MovieUIModel.Feature -> TYPE_FEATURED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_FEATURED -> {
                val binding = ItemFeaturedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemFeatureViewHolder(binding, onMovieClick)
            }
            else -> {
                val binding = ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                 ItemViewHolder(binding, onMovieClick)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = getItem(position)) {
            is MovieUIModel.Feature -> (holder as ItemFeatureViewHolder).binData(item.movie)
            is MovieUIModel.Movie -> (holder as ItemViewHolder).bindData(item.movie)
        }

    }

    // DiffUtil
    class DiffCallback : DiffUtil.ItemCallback<MovieUIModel>() {

        override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
            return when {
                oldItem is MovieUIModel.Feature && newItem is MovieUIModel.Feature -> oldItem.movie.id == newItem.movie.id
                oldItem is MovieUIModel.Movie && newItem is MovieUIModel.Movie -> oldItem.movie.id == newItem.movie.id
                else -> false
            }

        }

        override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
            return oldItem == newItem
        }
    }
}