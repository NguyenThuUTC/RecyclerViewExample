package com.example.recyclerviewex.data.remote

import com.example.recyclerviewex.data.remote.model.MovieCreditsResponse
import com.example.recyclerviewex.data.remote.model.MovieDetailResponse
import com.example.recyclerviewex.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>


    /**
     * Task 4: tạo api
     */
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieCreditsResponse>

}
