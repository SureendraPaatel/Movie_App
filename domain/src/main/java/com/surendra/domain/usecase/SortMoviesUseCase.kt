package com.surendra.domain.usecase

import com.surendra.domain.model.Movie
import com.surendra.domain.model.SortOption
import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SortMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<SortMoviesUseCase.Params, List<Movie>>() {

    override suspend fun invoke(params: SortMoviesUseCase.Params): Flow<Result<List<Movie>>> {

        return movieRepository.getSortedMovies(params.sortOption)
    }

    data class Params(val sortOption: SortOption)
}