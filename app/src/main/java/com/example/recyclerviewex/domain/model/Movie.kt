package com.example.recyclerviewex.domain.model

data class Movie(
    val id: Int,
    val title: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val posterUrl: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val genres: List<String> = emptyList(),
    val isFavorite: Boolean = false
)
