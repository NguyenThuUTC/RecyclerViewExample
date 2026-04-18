package com.example.recyclerviewex.ui.movies

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewex.domain.usecase.GetPopularMoviesUseCase
import com.example.recyclerviewex.domain.usecase.SetFavoriteMovieUseCase
import com.example.recyclerviewex.ui.mapper.toDomain
import com.example.recyclerviewex.ui.mapper.toMovieListPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val setFavoriteMovieUseCase: SetFavoriteMovieUseCase
) : ViewModel() {
    private val _movies = MutableLiveData<List<MovieUIModel>?>()
    val movies: LiveData<List<MovieUIModel>?> = _movies

    var job: Job? = null
    var currentPage = 1

    fun loadMovies() {
        if (job?.isActive == true) {
            Log.d("loadMovies", "skip due to loading in progress $currentPage")
            return
        }

        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("loadMovies", "start call api at page $currentPage")
                val data = getPopularMoviesUseCase(currentPage)
                if (data.isNotEmpty()) {
                    val uiList = data.toMovieListPresentation(includeFeatured = currentPage == 1)
                    currentPage++
                    _movies.postValue((_movies.value ?: emptyList()) + uiList)
                }
            } catch (e: Exception) {
                Log.e("Error", "${e.message}")
            }
        }
    }

    fun favoriteMovie(movie: MovieItem) {
        updateFavoriteState(movie.id, true)
        viewModelScope.launch(Dispatchers.IO) {
            setFavoriteMovieUseCase(
                movie = movie.toDomain(),
                isFavorite = true
            )
        }
    }

    private fun updateFavoriteState(movieId: Int?, isFavorite: Boolean) {
        if (movieId == null) return
        val updatedMovies = _movies.value?.map { item ->
            when (item) {
                is MovieUIModel.Feature -> {
                    if (item.movie.id == movieId) {
                        item.copy(movie = item.movie.copy(isFavorite = isFavorite))
                    } else {
                        item
                    }
                }

                is MovieUIModel.Movie -> {
                    if (item.movie.id == movieId) {
                        item.copy(movie = item.movie.copy(isFavorite = isFavorite))
                    } else {
                        item
                    }
                }
            }
        }
        _movies.postValue(updatedMovies)
    }
}
