package com.example.recyclerviewex.data.repository

import com.example.recyclerviewex.data.local.dao.MovieDao
import com.example.recyclerviewex.data.local.entity.GenreEntity
import com.example.recyclerviewex.data.local.entity.MovieEntity
import com.example.recyclerviewex.data.local.entity.MovieGenreCrossRef

class MovieRepository(private val dao: MovieDao) {

    suspend fun insertMovie(
        movie: MovieEntity,
    ) {
        dao.insertMovie(
            movie,
        )
    }

    suspend fun insertGenre(
        genre: List<GenreEntity>, movieGenre: List<MovieGenreCrossRef>,
    ) {
        dao.insertGenre(
            genre,
        )
        dao.insertMovieGenre(movieGenre)
    }

    fun getViewedMovies() = dao.getMovies()

    fun getFavoriteMovies() = dao.getFavoriteMovies()

    suspend fun getMovieById(movieId: Int) = dao.getMovieById(movieId)

    suspend fun saveViewedMovie(movie: MovieEntity) {
        val existingMovie = dao.getMovieById(movie.id)
        dao.upsertMovie(
            movie.copy(
                isFavorite = existingMovie?.isFavorite ?: movie.isFavorite
            )
        )
    }

    suspend fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        val existingMovie = dao.getMovieById(movie.id)
        if (existingMovie == null) {
            dao.upsertMovie(movie.copy(isFavorite = isFavorite))
            return
        }

        dao.upsertMovie(
            existingMovie.copy(
                originalTitle = movie.originalTitle ?: existingMovie.originalTitle,
                overview = movie.overview ?: existingMovie.overview,
                posterPath = movie.posterPath ?: existingMovie.posterPath,
                voteAverage = movie.voteAverage ?: existingMovie.voteAverage,
                viewedAt = existingMovie.viewedAt ?: movie.viewedAt,
                releaseDate = movie.releaseDate ?: existingMovie.releaseDate,
                isFavorite = isFavorite
            )
        )
    }

    suspend fun updateFavoriteStatus(movieId: Int, isFavorite: Boolean) {
        dao.updateFavoriteStatus(movieId, isFavorite)
    }
}
