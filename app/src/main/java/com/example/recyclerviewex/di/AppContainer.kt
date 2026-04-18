package com.example.recyclerviewex.di

import android.content.Context
import com.example.recyclerviewex.data.local.database.AppDatabaseProvider
import com.example.recyclerviewex.data.remote.ServiceProvider
import com.example.recyclerviewex.data.repository.MovieRepositoryImpl
import com.example.recyclerviewex.domain.repository.MovieRepository
import com.example.recyclerviewex.domain.usecase.GetPopularMoviesUseCase
import com.example.recyclerviewex.domain.usecase.SetFavoriteMovieUseCase

class AppContainer private constructor(context: Context) {
    private val database = AppDatabaseProvider.getInstance(context.applicationContext)
    private val apiService = ServiceProvider.api

    val movieRepository: MovieRepository = MovieRepositoryImpl(
        apiService = apiService,
        movieDao = database.movieDao()
    )

    val getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    val setFavoriteMovieUseCase = SetFavoriteMovieUseCase(movieRepository)

    companion object {
        @Volatile
        private var instance: AppContainer? = null

        fun from(context: Context): AppContainer {
            return instance ?: synchronized(this) {
                instance ?: AppContainer(context).also { instance = it }
            }
        }
    }
}
