package com.surendra.domain.usecase

import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieBookmarkedUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: String): Flow<Boolean> {
        return movieRepository.isMovieBookmarked(movieId)
    }
}