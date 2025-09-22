package com.surendra.data.source.local

import com.surendra.data.database.MovieDao
import com.surendra.data.database.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getAllBookmarkedMovies(): Flow<List<MovieEntity>> = movieDao.getAllBookmarkedMovies()

    suspend fun getBookmarkedMovie(movieId: String): MovieEntity? = movieDao.getBookmarkedMovie(movieId)

    fun isMovieBookmarked(movieId: String): Flow<Boolean> = movieDao.isMovieBookmarked(movieId)

    suspend fun insertBookmarkedMovie(movie: MovieEntity) = movieDao.insertBookmarkedMovie(movie)

    suspend fun deleteBookmarkedMovie(movieId: String) = movieDao.deleteBookmarkedMovieById(movieId)
}