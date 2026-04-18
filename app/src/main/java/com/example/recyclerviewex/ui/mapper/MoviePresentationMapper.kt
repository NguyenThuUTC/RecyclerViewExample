package com.example.recyclerviewex.ui.mapper

import com.example.recyclerviewex.domain.model.Movie
import com.example.recyclerviewex.ui.movies.MovieItem
import com.example.recyclerviewex.ui.movies.MovieUIModel

fun Movie.toPresentation(): MovieItem {
    return MovieItem(
        genres = genres,
        id = id,
        isFavorite = isFavorite,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterUrl,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun List<Movie>.toMovieListPresentation(includeFeatured: Boolean): List<MovieUIModel> {
    if (isEmpty()) return emptyList()

    val presentationItems = map { movie ->
        MovieUIModel.Movie(movie = movie.toPresentation())
    }.toMutableList()

    if (includeFeatured) {
        presentationItems[0] = MovieUIModel.Feature(first().toPresentation())
    }

    return presentationItems
}

fun MovieItem.toDomain(): Movie {
    return Movie(
        id = id ?: 0,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        posterUrl = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genres = genres.orEmpty(),
        isFavorite = isFavorite
    )
}
