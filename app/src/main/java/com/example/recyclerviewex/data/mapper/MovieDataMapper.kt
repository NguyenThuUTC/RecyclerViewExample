package com.example.recyclerviewex.data.mapper

import com.example.recyclerviewex.data.local.entity.MovieEntity
import com.example.recyclerviewex.data.remote.model.Movie
import com.example.recyclerviewex.domain.model.Movie as DomainMovie

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun Movie.toDomain(isFavorite: Boolean = false): DomainMovie {
    return DomainMovie(
        id = id ?: 0,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        posterUrl = posterPath.toPosterUrl(),
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        isFavorite = isFavorite
    )
}

fun DomainMovie.toMovieEntity(
    viewedAt: Long? = null,
    isFavorite: Boolean = this.isFavorite
): MovieEntity {
    return MovieEntity(
        id = id,
        originalTitle = title ?: originalTitle,
        overview = overview,
        posterPath = posterUrl.toPosterUrl(),
        voteAverage = voteAverage,
        viewedAt = viewedAt,
        releaseDate = releaseDate,
        isFavorite = isFavorite
    )
}

private fun String?.toPosterUrl(): String? {
    if (this.isNullOrBlank()) return null
    return if (startsWith("http")) this else "$IMAGE_BASE_URL$this"
}
