package com.example.recyclerviewex.data.repository

import com.example.recyclerviewex.data.local.dao.MovieDao
import com.example.recyclerviewex.data.local.entity.MovieEntity

class MovieRepository(private val dao: MovieDao) {

    suspend fun insertMovie(
        movie: MovieEntity,
    ) {
        dao.insertMovie(
            movie,
        )
    }

    fun getViewedMovies() = dao.getMovies()
}