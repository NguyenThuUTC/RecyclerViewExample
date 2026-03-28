package com.example.recyclerviewex.ui.movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewex.databinding.ItemMovieBinding
import com.example.recyclerviewex.ui.MovieItem

class ItemViewHolder(
    val binding: ItemMovieBinding,
    val onMovieClick: (MovieItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentMovie: MovieItem? = null
    init {
        binding.root.setOnClickListener {
            currentMovie?.let {
                onMovieClick.invoke(it)
            }
        }
    }
    fun bindData(movie: MovieItem) = binding.run {
        currentMovie = movie
        Glide.with(binding.root)
            .load(movie.posterPath)
            .into(imgPoster)
        txtTitle.text = movie.title
        txtOverview.text = movie.overview
    }
}