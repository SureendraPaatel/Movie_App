package com.surendra.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surendra.domain.usecase.GetBookmarkedMoviesUseCase
import com.surendra.domain.usecase.RemoveBookmarkUseCase
import com.surendra.presentation.ui.screen.bookmark.BookmarksUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val getBookmarkedMoviesUseCase: GetBookmarkedMoviesUseCase,
    private val removeBookmarkUseCase : RemoveBookmarkUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarksUiState())
    val uiState: StateFlow<BookmarksUiState> = _uiState.asStateFlow()

    init {
        loadBookmarkedMovies()
    }



    private fun loadBookmarkedMovies() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            getBookmarkedMoviesUseCase().collect { result ->
                result.fold(
                    onSuccess = { movies ->
                        _uiState.value = _uiState.value.copy(
                            bookmarkedMovies = movies,
                            isLoading = false,
                            error = null
                        )
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            bookmarkedMovies = emptyList(),
                            isLoading = false,
                            error = error.message ?: "Failed to load bookmarks"
                        )
                    }
                )
            }
        }
    }

    fun removeBookmark(movieId: String) {
        viewModelScope.launch {
            removeBookmarkUseCase(movieId)
        }
    }


}