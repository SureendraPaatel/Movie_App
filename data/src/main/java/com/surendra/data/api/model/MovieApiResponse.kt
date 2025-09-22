package com.surendra.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("genre")
    val genre: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("cast")
    val cast: String?,
    @SerializedName("duration")
    val duration: Int?
)
