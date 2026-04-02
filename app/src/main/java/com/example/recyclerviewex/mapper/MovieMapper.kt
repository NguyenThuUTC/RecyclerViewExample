package com.example.recyclerviewex.mapper

import com.example.recyclerviewex.data.model.Movie
import com.example.recyclerviewex.data.model.MovieCreditsResponse
import com.example.recyclerviewex.data.model.MovieDetailResponse
import com.example.recyclerviewex.ui.detail.MovieDetailUiModel
import com.example.recyclerviewex.ui.movies.MovieItem

fun Movie.toUi(): MovieItem {
    return MovieItem(
        id = id,
        title = title,
        overview = overview,
        posterPath = "$IMAGE_BASE_URL$posterPath"
    )
}


/**
 * Task 6: Mapping dữ liệu cho UI
 */
fun mapToMovieDetailUiModel(
    movie: MovieDetailResponse?,
    credits: MovieCreditsResponse?
): MovieDetailUiModel {
    return MovieDetailUiModel(
        id = movie?.id?.toString(),
        title = movie?.title,
        overview = movie?.overview,
        posterPath = "$IMAGE_BASE_URL${movie?.posterPath}",
        voteAverage = movie?.voteAverage,
        voteCount = movie?.voteCount,
        productionCompanies = movie?.productionCompanies
            ?.mapNotNull { it.name }
            ?.joinToString(", "),
        genres = movie?.genres,
        cast = credits?.cast
            ?.sortedBy { it.order }
            ?.take(10)?.map {
                it.copy(profilePath = "$IMAGE_BASE_URL${it.profilePath}")
            }
    )
}

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"