package com.surendra.domain.usecase

import com.surendra.domain.model.Movie
import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieRepsitory: MovieRepository
) : BaseUseCaseNoParams<List<Movie>>() {

    override suspend fun invoke(): Flow<Result<List<Movie>>> {
        return movieRepsitory.getMovies()
    }
}