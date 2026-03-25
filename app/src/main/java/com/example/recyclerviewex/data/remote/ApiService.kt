package com.example.recyclerviewex.data.remote

import com.example.recyclerviewex.data.model.Movie
import com.example.recyclerviewex.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>
}
