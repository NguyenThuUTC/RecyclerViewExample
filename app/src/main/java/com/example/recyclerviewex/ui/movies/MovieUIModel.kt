package com.example.recyclerviewex.ui.movies

data class MovieItem(
    val genres: List<String>? = null,
    val id: Int? = null,
    val isFavorite: Boolean = false,
    val originalTitle: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
)

//feature/move
sealed class MovieUIModel {
    data class Feature(val movie: MovieItem): MovieUIModel()
    data class Movie(val movie: MovieItem): MovieUIModel()
}
