package com.surendra.domain.model

data class Movie(
    val id : String,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val poster: String,
    val overview: String?,
    val genre: String?,
    val director: String?,
    val cast : String?,
    val duration: Int?,
    val isBookmarked: Boolean = false


)
