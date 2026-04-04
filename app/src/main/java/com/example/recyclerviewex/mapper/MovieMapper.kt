package com.example.recyclerviewex.mapper

import com.example.recyclerviewex.data.local.entity.MovieEntity
import com.example.recyclerviewex.data.model.Movie
import com.example.recyclerviewex.data.model.MovieCreditsResponse
import com.example.recyclerviewex.data.model.MovieDetailResponse
import com.example.recyclerviewex.ui.detail.MovieDetailUiModel
import com.example.recyclerviewex.ui.movies.MovieItem
import kotlin.Int


const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
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

fun MovieDetailResponse.toMovieEntity(viewedAt: Long?) = MovieEntity(
    id = id ?: 0,
    genreIds = this.genres?.mapNotNull { it.id }
        ?.joinToString(","),
    overview = overview,
    posterPath = "$IMAGE_BASE_URL${posterPath}",
    originalTitle = title,
    voteAverage = voteAverage,
    viewedAt = viewedAt
)


