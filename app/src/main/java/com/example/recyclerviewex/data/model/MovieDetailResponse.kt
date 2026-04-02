package com.example.recyclerviewex.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val adult: Boolean? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val revenue: Long? = null,
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)

data class Genre(
    val id: Int? = null,
    val name: String? = null
)

data class ProductionCompany(
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    val name: String? = null
)

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    val name: String? = null
)