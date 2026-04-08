package com.example.recyclerviewex.ui.history

import androidx.lifecycle.ViewModel
import com.example.recyclerviewex.data.repository.MovieRepository
import com.example.recyclerviewex.mapper.toMovieUiModel
import kotlinx.coroutines.flow.map

class HistoryViewModel(private val movieRepository: MovieRepository): ViewModel() {

    val movies = movieRepository.getViewedMovies().map {
        it.toMovieUiModel()
    }
}