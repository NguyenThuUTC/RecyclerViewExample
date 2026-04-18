package com.example.recyclerviewex.domain.usecase

import com.example.recyclerviewex.domain.model.Movie
import com.example.recyclerviewex.domain.repository.MovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(page: Int): List<Movie> {
        return movieRepository.getPopularMovies(page)
    }
}
