package com.example.recyclerviewex.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIds: List<Int>? = null,
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)