package com.example.recyclerviewex.domain.usecase

import com.example.recyclerviewex.domain.model.Movie
import com.example.recyclerviewex.domain.repository.MovieRepository

class SetFavoriteMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie, isFavorite: Boolean) {
        movieRepository.setFavoriteMovie(movie, isFavorite)
    }
}
