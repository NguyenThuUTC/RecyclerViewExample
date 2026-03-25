package com.example.recyclerviewex.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewex.databinding.ItemMovieBinding
import com.example.recyclerviewex.ui.MovieUIModel

class MovieAdapter : ListAdapter<MovieUIModel, MovieAdapter.ItemViewHolder>(DiffCallback()) {

    class ItemViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        Glide.with(holder.itemView)
            .load(item.posterPath)
            .into(holder.binding.imgPoster)
        holder.binding.txtTitle.text = item.title
        holder.binding.txtOverview.text = item.overview
    }

    // DiffUtil
    class DiffCallback : DiffUtil.ItemCallback<MovieUIModel>() {

        override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean {
            return oldItem == newItem
        }
    }
}