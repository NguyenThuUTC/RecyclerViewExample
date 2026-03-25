package com.example.recyclerviewex.mapper

import com.example.recyclerviewex.data.model.Movie
import com.example.recyclerviewex.ui.MovieUIModel

fun Movie.toUi(): MovieUIModel {
    return MovieUIModel(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/$poster_path"
    )
}