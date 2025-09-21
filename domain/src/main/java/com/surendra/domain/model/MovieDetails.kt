package com.surendra.domain.model

import kotlin.time.Duration

data class MovieDetails(
    val id: String,
    val title: String,
    val releaseDate : String,
    val poster: String,
    val rating : Double,
    val overview: String?,
    val genre : String?,
    val director: String?,
    val cast : String?,
    val duration: Int?,
    val isBookmarked: Boolean = false
)
