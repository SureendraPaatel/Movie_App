package com.surendra.domain.repository

import com.surendra.domain.model.Movie
import com.surendra.domain.model.MovieDetails
import com.surendra.domain.model.SortOption
import kotlinx.coroutines.flow.Flow
import java.util.Queue

interface MovieRepository {

    // Remote Api operations
    suspend fun getMovies() : Flow<Result<List<Movie>>>
    suspend fun getMovieDetails(movieId: String): Flow<Result<MovieDetails>>


    // Local database operations (Bookmarks)
    suspend fun getBookmarkedMovies() : Flow<List<Movie>>
    suspend fun bookmarkMovie(movie: Movie)
    suspend fun removeBookmark(movieId: String)
    suspend fun isMovieBookmarked(movieId: String): Flow<Boolean>

    // Search and sort functionality
    suspend fun searchMovies(query: String) : Flow<Result<List<Movie>>>
    suspend fun getSortedMovies(sortOption: SortOption) : Flow<Result<List<Movie>>>
    
}