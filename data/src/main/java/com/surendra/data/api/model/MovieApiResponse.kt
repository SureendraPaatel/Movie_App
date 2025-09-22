package com.surendra.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("createdAt")
    val createdAt: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("genre")
    val genre: List<String>,
    @SerializedName("rating")
    val rating: RatingResponse,
    @SerializedName("release_date")
    val releaseDate: Long, // Unix timestamp
    @SerializedName("poster_url")
    val posterUrl: String,
    @SerializedName("duration_minutes")
    val durationMinutes: Int,
    @SerializedName("director")
    val director: String,
    @SerializedName("cast")
    val cast: List<String>,
    @SerializedName("box_office_usd")
    val boxOfficeUsd: Long,
    @SerializedName("description")
    val description: String
)

data class RatingResponse(
    @SerializedName("imdb")
    val imdb: Double
)