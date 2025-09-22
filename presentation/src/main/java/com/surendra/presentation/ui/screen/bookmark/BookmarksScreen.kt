package com.surendra.presentation.ui.screen.bookmark

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.surendra.domain.model.Movie
import com.surendra.presentation.ui.component.*
import com.surendra.presentation.ui.component.LoadingComponent
import com.surendra.presentation.viewModel.BookmarksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel,
    onMovieClick: (Movie) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { Text("Bookmarked Movies") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        // Content
        when {
            uiState.isLoading -> {
                LoadingComponent()
            }

            uiState.error != null -> {
                ErrorComponent(
                    error = uiState.error!!,
                    onRetry = { /* Bookmarks auto-refresh */ }
                )
            }

            uiState.bookmarkedMovies.isEmpty() -> {
                EmptyStateComponent(
                    message = "No bookmarked movies yet.\nStart adding your favorites!"
                )
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.bookmarkedMovies) { movie ->
                        MovieItem(
                            movie = movie,
                            onMovieClick = onMovieClick,
                            onBookmarkClick = { viewModel.removeBookmark(it.id) }
                        )
                    }
                }
            }
        }
    }
}