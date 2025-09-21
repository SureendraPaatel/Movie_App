package com.surendra.domain.usecase

import com.surendra.domain.model.MovieDetails
import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<GetMovieDetailsUseCase.Params, MovieDetails>(){

    override suspend fun invoke(params: GetMovieDetailsUseCase.Params): Flow<Result<MovieDetails>> {
        return movieRepository.getMovieDetails(params.movieId)
    }

    data class Params(val movieId: String)
}