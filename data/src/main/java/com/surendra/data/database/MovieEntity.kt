package com.surendra.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "bookmarked_movies")
@TypeConverters(Converters::class)
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "genre")
    val genre: List<String>,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "poster_url")
    val posterUrl: String,

    @ColumnInfo(name = "duration_minutes")
    val durationMinutes: Int,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "cast")
    val cast: List<String>,

    @ColumnInfo(name = "box_office_usd")
    val boxOfficeUsd: Long,

    @ColumnInfo(name = "description")
    val description: String,


     @ColumnInfo(name = "bookmarked_at")
     val bookmarkedAt: Long = System.currentTimeMillis()


)

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }
}