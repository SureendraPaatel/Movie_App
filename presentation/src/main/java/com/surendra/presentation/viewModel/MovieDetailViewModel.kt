package com.surendra.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surendra.domain.model.Movie
import com.surendra.domain.usecase.*
import com.surendra.presentation.ui.screen.movieDetail.MovieDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val bookmarkMovieUseCase: BookmarkMovieUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val isMovieBookmarkedUseCase: IsMovieBookmarkedUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()


    fun loadMovieDetails(movieId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            // Load movie details
            getMovieDetailsUseCase(GetMovieDetailsUseCase.Params(movieId)).collect { result ->
                result.fold(
                    onSuccess = { movieDetails ->
                        _uiState.value = _uiState.value.copy(
                            movieDetails = movieDetails,
                            isLoading = false,
                            error = null,
                            isBookmarked = movieDetails.isBookmarked
                        )
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            movieDetails = null,
                            isLoading = false,
                            error = error.message ?: "Failed to load movie details"
                        )
                    }
                )
            }
        }
    }

    fun toggleBookmark() {
        val movieDetails = _uiState.value.movieDetails ?: return

        viewModelScope.launch {
            if (_uiState.value.isBookmarked) {
                removeBookmarkUseCase(movieDetails.id)
            } else {
                val movie = Movie(
                    id = movieDetails.id,
                    title = movieDetails.title,
                    releaseDate = movieDetails.releaseDate,
                    rating = movieDetails.rating,
                    posterUrl = movieDetails.posterUrl,
                    genre = movieDetails.genre,
                    director = movieDetails.director,
                    cast = movieDetails.cast,
                    durationMinutes = movieDetails.durationMinutes,
                    isBookmarked = false,
                    boxOfficeUsd = movieDetails.boxOfficeUsd,
                    description = movieDetails.description
                )
                bookmarkMovieUseCase(movie)
            }

            _uiState.value = _uiState.value.copy(isBookmarked = !_uiState.value.isBookmarked)
        }
    }

    fun retry(movieId: String) {
        loadMovieDetails(movieId)
    }


}