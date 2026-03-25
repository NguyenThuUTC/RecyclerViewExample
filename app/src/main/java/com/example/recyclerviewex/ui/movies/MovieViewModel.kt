package com.example.recyclerviewex.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewex.data.remote.ServiceProvider
import com.example.recyclerviewex.mapper.toUi
import com.example.recyclerviewex.ui.MovieUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<MovieUIModel>>()
    val movies: LiveData<List<MovieUIModel>> = _movies

    val service = ServiceProvider.api

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getPopularMovies("e5ab9840aeaee234fbaefbbdb86adaae")

                if (response.isSuccessful) {
                    val data = response.body()?.results
                    val uiList = data?.map { it.toUi() }
                    _movies.postValue(uiList)
                }
            } catch (e: Exception) {
                Log.e("Error", "${e.message}")
            }


        }
    }
}