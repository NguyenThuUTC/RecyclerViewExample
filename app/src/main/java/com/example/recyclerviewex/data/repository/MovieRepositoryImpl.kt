package com.example.recyclerviewex.data.repository

import com.example.recyclerviewex.data.local.dao.MovieDao
import com.example.recyclerviewex.data.mapper.toDomain
import com.example.recyclerviewex.data.mapper.toMovieEntity
import com.example.recyclerviewex.data.remote.ApiResult
import com.example.recyclerviewex.data.remote.ApiService
import com.example.recyclerviewex.data.remote.safeApiCall
import com.example.recyclerviewex.domain.model.Movie
import com.example.recyclerviewex.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return when (
            val result = safeApiCall {
                apiService.getPopularMovies(
                    apiKey = "e5ab9840aeaee234fbaefbbdb86adaae",
                    page = page
                )
            }
        ) {
            is ApiResult.Success -> {
                val movies = result.data.results.orEmpty()
                if (movies.isEmpty()) return emptyList()

                val favoriteIds = movieDao.getFavoriteMovieIds(
                    movies.mapNotNull { it.id }.distinct()
                ).toSet()

                movies.map { movie ->
                    movie.toDomain(isFavorite = favoriteIds.contains(movie.id))
                }
            }

            is ApiResult.Error -> emptyList()
        }
    }

    override suspend fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        val existingMovie = movieDao.getMovieById(movie.id)
        val incomingMovie = movie.toMovieEntity(isFavorite = isFavorite)

        if (existingMovie == null) {
            movieDao.upsertMovie(incomingMovie)
            return
        }

        movieDao.upsertMovie(
            existingMovie.copy(
                originalTitle = incomingMovie.originalTitle ?: existingMovie.originalTitle,
                overview = incomingMovie.overview ?: existingMovie.overview,
                posterPath = incomingMovie.posterPath ?: existingMovie.posterPath,
                voteAverage = incomingMovie.voteAverage ?: existingMovie.voteAverage,
                viewedAt = existingMovie.viewedAt ?: incomingMovie.viewedAt,
                releaseDate = incomingMovie.releaseDate ?: existingMovie.releaseDate,
                isFavorite = isFavorite
            )
        )
    }
}
