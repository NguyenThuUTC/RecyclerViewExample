package com.example.recyclerviewex.ui.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewex.R
import com.example.recyclerviewex.databinding.ItemFeaturedBinding

class ItemFeatureViewHolder(
    val binding: ItemFeaturedBinding,
    val onMovieClick: (MovieItem) -> Unit,
    val onFavoriteClick: ((MovieItem) -> Unit)?,
    private val showFavoriteAction: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    private var currentMovie: MovieItem? = null
    init {
        binding.root.setOnClickListener {
            currentMovie?.let {
                onMovieClick.invoke(it)
            }
        }
        binding.btnFavorite.setOnClickListener {
            currentMovie?.let {
                onFavoriteClick?.invoke(it)
            }
        }
    }
    fun binData(movie: MovieItem) = binding.run {
        currentMovie = movie
        Glide.with(binding.root)
            .load(movie.posterPath)
            .into(imgPoster)
        txtTitle.text = movie.title
        btnFavorite.visibility = if (showFavoriteAction) View.VISIBLE else View.GONE
        btnFavorite.setImageResource(
            if (movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_un_favorite
        )
        btnFavorite.contentDescription = root.context.getString(
            if (movie.isFavorite) R.string.remove_favorite_movie else R.string.favorite_movie
        )
    }
}
