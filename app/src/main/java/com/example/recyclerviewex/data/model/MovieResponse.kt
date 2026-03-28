package com.example.recyclerviewex.data.model

data class MovieResponse(
    val results: List<Movie> ? = null,
    val page: Int? = null,
    val total_pages: Int? = null,
)