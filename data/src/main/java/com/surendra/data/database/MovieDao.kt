package com.surendra.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surendra.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM bookmarked_movies ORDER BY bookmarked_at DESC")
    fun getAllBookmarkedMovies() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM bookmarked_movies WHERE id = :movieId LIMIT 1")
    suspend fun getBookmarkedMovie(movieId: String): MovieEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_movies WHERE id = :movieId)")
    fun isMovieBookmarked(movieId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteBookmarkedMovie(movie: MovieEntity)

    @Query("DELETE FROM bookmarked_movies WHERE id = :movieId")
    suspend fun deleteBookmarkedMovieById(movieId: String)

    @Query("DELETE FROM bookmarked_movies")
    suspend fun clearAllBookmarks()


}