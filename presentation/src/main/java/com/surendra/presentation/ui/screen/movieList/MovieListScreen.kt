package com.surendra.presentation.ui.screen.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.surendra.domain.model.Movie
import com.surendra.presentation.ui.component.EmptyStateComponent
import com.surendra.presentation.ui.component.ErrorComponent
import com.surendra.presentation.ui.component.LoadingComponent
import com.surendra.presentation.ui.component.MovieItem
import com.surendra.presentation.ui.component.SortDialog
import com.surendra.presentation.viewModel.MovieListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (Movie) -> Unit,
    onBookmarksClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { Text("Movies") },
            actions = {
                IconButton(onClick = { viewModel.showSortDialog() }) {
                    Icon(Icons.Default.Sort, contentDescription = "Sort")
                }
                IconButton(onClick = onBookmarksClick) {
                    Icon(Icons.Default.Bookmark, contentDescription = "Bookmarks")
                }
            }
        )

        // Search Bar
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = viewModel::searchMovies,
            label = { Text("Search movies...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        // Content
        when {
            uiState.isLoading -> {
                LoadingComponent()
            }

            uiState.error != null -> {
                ErrorComponent(
                    error = uiState.error!!,
                    onRetry = viewModel::retry
                )
            }

            uiState.movies.isEmpty() -> {
                EmptyStateComponent(
                    message = if (uiState.searchQuery.isNotEmpty()) {
                        "No movies found for '${uiState.searchQuery}'"
                    } else {
                        "No movies available"
                    }
                )
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.movies) { movie ->
                        MovieItem(
                            movie = movie,
                            onMovieClick = onMovieClick,
                            onBookmarkClick = viewModel::toggleBookmark
                        )
                    }
                }
            }
        }
    }

    // Sort Dialog
    if (uiState.showSortDialog) {
        SortDialog(
            currentSortOption = uiState.sortOption,
            onSortOptionSelected = viewModel::sortMovies,
            onDismiss = viewModel::hideSortDialog
        )
    }
}