package com.surendra.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surendra.domain.model.Movie
import com.surendra.domain.model.SortOption
import com.surendra.domain.usecase.*
import com.surendra.presentation.ui.screen.movieList.MovieListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val sortMoviesUseCase: SortMoviesUseCase,
    private val bookmarkMovieUseCase: BookmarkMovieUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase

    ) : ViewModel() {

        private val _uiState = MutableStateFlow(MovieListUiState())
        val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }


    fun loadMovies() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            getMoviesUseCase().collect { result ->
                result.fold(
                    onSuccess = { movies ->
                        _uiState.value = _uiState.value.copy(
                            movies = movies,
                            isLoading = false,
                            error = null
                        )
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            movies = emptyList(),
                            isLoading = false,
                            error = error.message ?: "Unknown error occurred"
                        )
                    }
                )
            }
        }
    }

    fun searchMovies(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)

        if (query.isEmpty()) {
            loadMovies()
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            searchMoviesUseCase(SearchMoviesUseCase.Params(query)).collect { result ->
                result.fold(
                    onSuccess = { movies ->
                        _uiState.value = _uiState.value.copy(
                            movies = movies,
                            isLoading = false,
                            error = null
                        )
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            movies = emptyList(),
                            isLoading = false,
                            error = error.message ?: "Search failed"
                        )
                    }
                )
            }
        }
    }

    fun sortMovies(sortOption: SortOption) {
        _uiState.value = _uiState.value.copy(sortOption = sortOption, showSortDialog = false)

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            sortMoviesUseCase(SortMoviesUseCase.Params(sortOption)).collect { result ->
                result.fold(
                    onSuccess = { movies ->
                        _uiState.value = _uiState.value.copy(
                            movies = movies,
                            isLoading = false,
                            error = null
                        )
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            movies = emptyList(),
                            isLoading = false,
                            error = error.message ?: "Sorting failed"
                        )
                    }
                )
            }
        }
    }

    fun toggleBookmark(movie: Movie) {
        viewModelScope.launch {
            if (movie.isBookmarked) {
                removeBookmarkUseCase(movie.id)
            } else {
                bookmarkMovieUseCase(movie)
            }
            // Refresh movies to update bookmark status
            loadMovies()
        }
    }

    fun showSortDialog() {
        _uiState.value = _uiState.value.copy(showSortDialog = true)
    }

    fun hideSortDialog() {
        _uiState.value = _uiState.value.copy(showSortDialog = false)
    }

    fun retry() {
        loadMovies()
    }


}