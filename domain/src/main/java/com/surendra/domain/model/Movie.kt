package com.surendra.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: String,
    val title: String,
    val genre: List<String>,
    val rating: Double, // imdb rating
    val releaseDate: String, // formatted date from timestamp
    val posterUrl: String,
    val durationMinutes: Int,
    val director: String,
    val cast: List<String>,
    val boxOfficeUsd: Long,
    val description: String,
    val isBookmarked: Boolean = false
)
