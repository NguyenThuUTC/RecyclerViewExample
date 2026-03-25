package com.example.recyclerviewex.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNWFiOTg0MGFlYWVlMjM0ZmJhZWZiYmRiODZhZGFhZSIsIm5iZiI6MTQ5NjM4NDYzOC40NDcsInN1YiI6IjU5MzEwNDcwOTI1MTQxMTVlYTAwOGEwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.s88LdVUPYUqsYIjRUZ3mlwTz73qV-aJPMXKHQ4zxxy8")
            .build()

        Log.d("AuthInterceptor", "Added Authorization header")

        // before send REQUEST
        Log.d("AuthInterceptor", "Sending request: ${newRequest.url}")
        Log.d("AuthInterceptor", "Method: ${newRequest.method}")


        val startTime = System.currentTimeMillis()

        val response = chain.proceed(newRequest) // send request

        // after received RESPONSE
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime

        Log.d(
            "AuthInterceptor",
            "Received response for ${response.request.url} in ${duration}ms"
        )
        Log.d("AuthInterceptor", "Status code: ${response.code}")

        return response
    }
}
