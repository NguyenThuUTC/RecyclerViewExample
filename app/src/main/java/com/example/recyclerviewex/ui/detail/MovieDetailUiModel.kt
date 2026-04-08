package com.example.recyclerviewex.ui.detail

import com.example.recyclerviewex.data.remote.model.Cast
import com.example.recyclerviewex.data.remote.model.Genre

data class MovieDetailUiModel(
    val id: String? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val productionCompanies: String? = null,
    val genres: List<Genre>? = null,
    val cast: List<Cast>? = null
)