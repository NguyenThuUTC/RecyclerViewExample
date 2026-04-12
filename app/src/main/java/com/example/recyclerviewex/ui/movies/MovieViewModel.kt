package com.example.recyclerviewex.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewex.data.repository.MovieRepository
import com.example.recyclerviewex.data.remote.ServiceProvider
import com.example.recyclerviewex.mapper.toMovieEntity
import com.example.recyclerviewex.mapper.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movies = MutableLiveData<List<MovieUIModel>?>()
    val movies: LiveData<List<MovieUIModel>?> = _movies

    val service = ServiceProvider.api

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
                val response =
                    service.getPopularMovies("e5ab9840aeaee234fbaefbbdb86adaae", currentPage)
                if (response.isSuccessful) {
                    val data = response.body()?.results ?: emptyList()
                    val uiList = arrayListOf<MovieUIModel>()
                    if (currentPage == 1 && data.isNotEmpty()) {
                        uiList.add(MovieUIModel.Feature(data.first().toUi()))
                    }

                    val startIndex = if (currentPage == 1) 1 else 0

                    uiList.addAll(data.drop(startIndex).map {
                        MovieUIModel.Movie(
                            it.toUi()
                        )
                    })

                    currentPage++

                    _movies.postValue((_movies.value ?: emptyList()) + uiList)
                } else {
                    Log.e("loadMovies", "error ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Error", "${e.message}")
            }
        }
    }

    fun favoriteMovie(movie: MovieItem) {
        updateFavoriteState(movie.id, true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavoriteMovie(
                movie = movie.toMovieEntity(isFavorite = true),
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
