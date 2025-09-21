package com.surendra.domain.usecase

import com.surendra.domain.model.Movie
import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBookmarkedMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCaseNoParams<List<Movie>>(){
    override suspend fun invoke(): Flow<Result<List<Movie>>> {

        return movieRepository.getBookmarkedMovies().map { Result.success(it) }
    }

}