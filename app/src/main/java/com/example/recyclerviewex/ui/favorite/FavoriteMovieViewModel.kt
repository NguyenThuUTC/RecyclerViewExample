package com.example.recyclerviewex.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewex.data.repository.MovieRepository
import com.example.recyclerviewex.mapper.toMovieUiModel
import com.example.recyclerviewex.ui.movies.MovieItem
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val favoriteMovies = movieRepository.getFavoriteMovies().map {
        it.toMovieUiModel().orEmpty()
    }

    fun removeFavorite(movie: MovieItem) {
        val movieId = movie.id ?: return
        viewModelScope.launch {
            movieRepository.updateFavoriteStatus(movieId, false)
        }
    }
}
