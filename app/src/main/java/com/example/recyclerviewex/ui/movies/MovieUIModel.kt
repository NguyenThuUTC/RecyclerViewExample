package com.example.recyclerviewex.ui.movies

import com.example.recyclerviewex.data.remote.model.Genre

data class MovieItem(
    val genres: List<String>? = null,
    val id: Int? = null,
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
