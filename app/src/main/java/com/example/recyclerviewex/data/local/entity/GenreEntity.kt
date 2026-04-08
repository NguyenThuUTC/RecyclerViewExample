package com.example.recyclerviewex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String? = null
)