package com.example.recyclerviewex.domain.repository

import com.example.recyclerviewex.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun setFavoriteMovie(movie: Movie, isFavorite: Boolean)
}
