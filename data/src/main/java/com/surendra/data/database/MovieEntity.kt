package com.surendra.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "rating")
    val rating : Double,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "genre")
    val genre: String?,

    @ColumnInfo(name = "director")
    val director : String?,

    @ColumnInfo(name = "cast")
    val cast : String?,

    @ColumnInfo(name = "duration")
    val duration : Int?,

    @ColumnInfo(name = "bookmarked_at")
    val bookmarkedAt : Long = System.currentTimeMillis()

)
