package com.surendra.data.source.remote

import com.surendra.data.api.MovieApiService
import com.surendra.data.api.model.MovieApiResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiService: MovieApiService
) {

    suspend fun getMovies(): Response<List<MovieApiResponse>> = apiService.getMovies()

    suspend fun getMovieDetails(movieId: String): Response<MovieApiResponse> = apiService.getMovieDetails(movieId)
}