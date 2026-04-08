package com.example.recyclerviewex.data.remote.model

import com.google.gson.annotations.SerializedName

class MovieCreditsResponse(
    val id: Int? = null,
    val cast: List<Cast>? = null,
    val crew: List<Crew>? = null
)

data class Cast(
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null,
    @SerializedName("cast_id")
    val castId: Int? = null,
    val character: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    val order: Int? = null
)

data class Crew(
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    val department: String? = null,
    val job: String? = null
)