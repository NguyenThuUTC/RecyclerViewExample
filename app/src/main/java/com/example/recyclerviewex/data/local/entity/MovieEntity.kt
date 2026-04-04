package com.example.recyclerviewex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    val genreIds: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val viewedAt: Long? = null
)