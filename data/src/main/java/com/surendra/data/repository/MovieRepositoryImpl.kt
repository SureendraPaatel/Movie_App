package com.surendra.data.repository

import com.surendra.data.mapper.MovieMapper
import com.surendra.data.source.local.MovieLocalDataSource
import com.surendra.data.source.remote.MovieRemoteDataSource
import com.surendra.domain.model.Movie
import com.surendra.domain.model.MovieDetails
import com.surendra.domain.model.SortOption
import com.surendra.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
): MovieRepository {
    override suspend fun getMovies(): Flow<Result<List<Movie>>> = flow {
        try {
            val response = remoteDataSource.getMovies()
            if (response.isSuccessful && response.body() != null) {
                val movies = response.body()!!.map { apiResponse ->
                    val movie = MovieMapper.mapApiResponseToDomain(apiResponse)
                    // Check if movie is bookmarked
                    val isBookmarked = localDataSource.isMovieBookmarked(movie.id).first()
                    movie.copy(isBookmarked = isBookmarked)
                }
                emit(Result.success(movies))
            } else {
                emit(Result.failure(Exception("Failed to load movies: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getMovieDetails(movieId: String): Flow<Result<MovieDetails>> = flow {
        try {
            val response = remoteDataSource.getMovieDetails(movieId)
            if (response.isSuccessful && response.body() != null) {
                val movieDetails = MovieMapper.mapApiResponseToMovieDetails(response.body()!!)
                // Check if movie is bookmarked
                val isBookmarked = localDataSource.isMovieBookmarked(movieId).first()
                emit(Result.success(movieDetails.copy(isBookmarked = isBookmarked)))
            } else {
                emit(Result.failure(Exception("Failed to load movie details: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getBookmarkedMovies(): Flow<List<Movie>> {
        return localDataSource.getAllBookmarkedMovies().map { entities ->
            entities.map { MovieMapper.mapEntityToDomain(it) }
        }
    }

    override suspend fun bookmarkMovie(movie: Movie) {
        val movieEntity = MovieMapper.mapDomainToEntity(movie)
        localDataSource.insertBookmarkedMovie(movieEntity)
    }

    override suspend fun removeBookmark(movieId: String) {
        localDataSource.deleteBookmarkedMovie(movieId)
    }

    override suspend fun isMovieBookmarked(movieId: String): Flow<Boolean> {
        return localDataSource.isMovieBookmarked(movieId)
    }

    override suspend fun searchMovies(query: String): Flow<Result<List<Movie>>> = flow {
        try {
            val response = remoteDataSource.getMovies()
            if (response.isSuccessful && response.body() != null) {
                val allMovies = response.body()!!.map { MovieMapper.mapApiResponseToDomain(it) }
                val filteredMovies = allMovies.filter { movie ->
                    movie.title.contains(query, ignoreCase = true)
                }

                // Update bookmark status for filtered movies
                val moviesWithBookmarkStatus = filteredMovies.map { movie ->
                    val isBookmarked = localDataSource.isMovieBookmarked(movie.id).first()
                    movie.copy(isBookmarked = isBookmarked)
                }

                emit(Result.success(moviesWithBookmarkStatus))
            } else {
                emit(Result.failure(Exception("Failed to search movies: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getSortedMovies(sortOption: SortOption): Flow<Result<List<Movie>>> = flow {
        try {
            val response = remoteDataSource.getMovies()
            if (response.isSuccessful && response.body() != null) {
                val movies = response.body()!!.map { MovieMapper.mapApiResponseToDomain(it) }

                val sortedMovies = when (sortOption) {
                    SortOption.TITLE_ASC -> movies.sortedBy { it.title }
                    SortOption.TITLE_DESC -> movies.sortedByDescending { it.title }
                    SortOption.RELEASE_DATE_ASC -> movies.sortedBy { it.releaseDate }
                    SortOption.RELEASE_DATE_DESC -> movies.sortedByDescending { it.releaseDate }
                    SortOption.RATING_ASC -> movies.sortedBy { it.rating }
                    SortOption.RATING_DESC -> movies.sortedByDescending { it.rating }
                }

                // Update bookmark status for sorted movies
                val moviesWithBookmarkStatus = sortedMovies.map { movie ->
                    val isBookmarked = localDataSource.isMovieBookmarked(movie.id).first()
                    movie.copy(isBookmarked = isBookmarked)
                }

                emit(Result.success(moviesWithBookmarkStatus))
            } else {
                emit(Result.failure(Exception("Failed to sort movies: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}