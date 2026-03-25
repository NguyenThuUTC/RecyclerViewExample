package com.example.recyclerviewex.data.remote

import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResult.Success(body)
            } else {
                ApiResult.Error("Empty response")
            }
        } else {
            ApiResult.Error("Error code: ${response.code()}")
        }

    } catch (e: IOException) {
        ApiResult.Error("No internet connection")
    } catch (e: Exception) {
        ApiResult.Error("Unexpected error")
    }
}

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}