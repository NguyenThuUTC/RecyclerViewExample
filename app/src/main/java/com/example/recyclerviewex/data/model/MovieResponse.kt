package com.example.recyclerviewex.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie> ? = null,
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
)