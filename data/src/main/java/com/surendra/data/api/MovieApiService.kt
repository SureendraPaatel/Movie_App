package com.surendra.data.api

import com.surendra.data.api.model.MovieApiResponse
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movies")
    suspend fun getMovies(): Response<List<MovieApiResponse>>

    @GET("movies/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: String ): Response<MovieApiResponse>
}