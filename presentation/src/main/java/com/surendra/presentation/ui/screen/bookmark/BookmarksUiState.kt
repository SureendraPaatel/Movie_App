package com.surendra.presentation.ui.screen.bookmark

import com.surendra.domain.model.Movie

data class BookmarksUiState(
    val bookmarkedMovies: List<Movie> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null
)
