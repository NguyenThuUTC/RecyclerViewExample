package com.example.recyclerviewex.mapper

import com.example.recyclerviewex.data.local.entity.GenreEntity
import com.example.recyclerviewex.data.local.entity.MovieEntity
import com.example.recyclerviewex.data.local.entity.MovieGenreCrossRef
import com.example.recyclerviewex.data.local.model.MovieWithGenreDetail
import com.example.recyclerviewex.data.remote.model.Genre
import com.example.recyclerviewex.data.remote.model.Movie
import com.example.recyclerviewex.data.remote.model.MovieCreditsResponse
import com.example.recyclerviewex.data.remote.model.MovieDetailResponse
import com.example.recyclerviewex.ui.detail.MovieDetailUiModel
import com.example.recyclerviewex.ui.movies.MovieItem
import com.example.recyclerviewex.ui.movies.MovieUIModel
import kotlin.Int


const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
fun Movie.toUi(): MovieItem {
    return MovieItem(
        id = id,
        originalTitle = originalTitle,
        releaseDate = releaseDate,
        title = title,
        overview = overview,
        posterPath = posterPath.toPosterUrl(),
        voteAverage = voteAverage
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
    overview = overview,
    posterPath = posterPath.toPosterUrl(),
    originalTitle = title,
    voteAverage = voteAverage,
    viewedAt = viewedAt,
    releaseDate = releaseDate
)

fun MovieItem.toMovieEntity(viewedAt: Long? = null, isFavorite: Boolean = this.isFavorite) = MovieEntity(
    id = id ?: 0,
    originalTitle = title ?: originalTitle,
    overview = overview,
    posterPath = posterPath.toPosterUrl(),
    voteAverage = voteAverage,
    viewedAt = viewedAt,
    releaseDate = releaseDate,
    isFavorite = isFavorite
)

fun List<Genre>?.toGenreEntity(): List<GenreEntity>? {
    if (this?.isEmpty() == true) return null

    return this?.map { genre ->
        GenreEntity(
            id = genre.id ?: 0,
            name = genre.name ?: ""
        )
    }
}

fun List<Genre>?.toMovieGenreCross(movieId: Int): List<MovieGenreCrossRef>? {
    return this?.map { genre ->
        MovieGenreCrossRef(
            movieId = movieId,
            genreId = genre.id ?: 0
        )
    }
}

fun List<MovieWithGenreDetail>?.toMovieUiModel(): List<MovieUIModel>? {
    return this?.map { movieWithGenre ->
        MovieUIModel.Movie(
            movie = MovieItem(
                id = movieWithGenre.movie.id,
                isFavorite = movieWithGenre.movie.isFavorite,
                title = movieWithGenre.movie.originalTitle,
                overview = movieWithGenre.movie.overview,
                posterPath = movieWithGenre.movie.posterPath.toPosterUrl(),
                releaseDate = movieWithGenre.movie.releaseDate,
                voteAverage = movieWithGenre.movie.voteAverage,
                genres = movieWithGenre.genres.toGenre()
            ),

        )
    }
}

fun List<GenreEntity>?.toGenre(): List<String>? {
    return this?.map { genreEntity ->
        genreEntity.name ?: ""
    }
}

private fun String?.toPosterUrl(): String? {
    if (this.isNullOrBlank()) return null
    return if (startsWith("http")) this else "$IMAGE_BASE_URL$this"
}



