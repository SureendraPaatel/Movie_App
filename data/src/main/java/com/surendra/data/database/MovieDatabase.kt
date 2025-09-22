package com.surendra.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        const val DATABASE_NAME = "movie_database"

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase{
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}