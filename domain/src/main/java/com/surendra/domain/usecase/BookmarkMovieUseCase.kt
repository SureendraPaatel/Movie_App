package com.surendra.domain.usecase

import com.surendra.domain.model.Movie
import com.surendra.domain.repository.MovieRepository
import javax.inject.Inject

class BookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie){
        movieRepository.bookmarkMovie(movie)
    }
}