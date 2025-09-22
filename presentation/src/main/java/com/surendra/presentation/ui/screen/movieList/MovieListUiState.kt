package com.surendra.presentation.ui.screen.movieList

import com.surendra.domain.model.Movie
import com.surendra.domain.model.SortOption

data class MovieListUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val sortOption: SortOption = SortOption.TITLE_ASC,
    val showSortDialog: Boolean = false
)
