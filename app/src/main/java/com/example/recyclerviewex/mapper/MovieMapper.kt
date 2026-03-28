package com.example.recyclerviewex.mapper

import com.example.recyclerviewex.data.model.Movie
import com.example.recyclerviewex.ui.MovieItem

fun Movie.toUi(): MovieItem {
    return MovieItem(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w500$poster_path"
    )
}