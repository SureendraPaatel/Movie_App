package com.surendra.domain.usecase

import com.surendra.domain.model.Movie
import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<SearchMoviesUseCase.Params, List<Movie>>() {


    override suspend fun invoke(params: Params): Flow<Result<List<Movie>>> {
        return movieRepository.searchMovies(params.query)
    }

    data class Params(val query : String)
}