package com.surendra.presentation.ui.screen.movieDetail

import com.surendra.domain.model.MovieDetails

data class MovieDetailUiState(
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isBookmarked: Boolean = false
)
