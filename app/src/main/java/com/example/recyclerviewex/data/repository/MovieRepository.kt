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
}