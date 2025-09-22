package com.surendra.data.mapper

import com.surendra.data.api.model.MovieApiResponse
import com.surendra.data.database.MovieEntity
import com.surendra.domain.model.Movie
import com.surendra.domain.model.MovieDetails
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object MovieMapper {

    private fun formatDate(timestamp: Long): String {
        val date = Date(timestamp * 1000L) // Convert to milliseconds
        val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return formatter.format(date)
    }

    fun mapApiResponseToDomain(apiResponse: MovieApiResponse): Movie {
        return Movie(
            id = apiResponse.id,
            title = apiResponse.title,
            genre = apiResponse.genre,
            rating = apiResponse.rating.imdb,
            releaseDate = formatDate(apiResponse.releaseDate),
            posterUrl = apiResponse.posterUrl,
            durationMinutes = apiResponse.durationMinutes,
            director = apiResponse.director,
            cast = apiResponse.cast,
            boxOfficeUsd = apiResponse.boxOfficeUsd,
            description = apiResponse.description,
            isBookmarked = false // Will be updated based on local DB
        )
    }

    fun mapApiResponseToMovieDetails(apiResponse: MovieApiResponse): MovieDetails {
        return MovieDetails(
            id = apiResponse.id,
            title = apiResponse.title,
            genre = apiResponse.genre,
            rating = apiResponse.rating.imdb,
            releaseDate = formatDate(apiResponse.releaseDate),
            posterUrl = apiResponse.posterUrl,
            durationMinutes = apiResponse.durationMinutes,
            director = apiResponse.director,
            cast = apiResponse.cast,
            boxOfficeUsd = apiResponse.boxOfficeUsd,
            description = apiResponse.description,
            isBookmarked = false // Will be updated based on local DB
        )
    }

    fun mapDomainToEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            title = movie.title,
            genre = movie.genre,
            rating = movie.rating,
            releaseDate = movie.releaseDate,
            posterUrl = movie.posterUrl,
            durationMinutes = movie.durationMinutes,
            director = movie.director,
            cast = movie.cast,
            boxOfficeUsd = movie.boxOfficeUsd,
            description = movie.description
        )
    }

    fun mapEntityToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            genre = entity.genre,
            rating = entity.rating,
            releaseDate = entity.releaseDate,
            posterUrl = entity.posterUrl,
            durationMinutes = entity.durationMinutes,
            director = entity.director,
            cast = entity.cast,
            boxOfficeUsd = entity.boxOfficeUsd,
            description = entity.description,
            isBookmarked = true
        )
    }

    fun formatDuration(minutes: Int?): String {
        if (minutes == null || minutes <= 0) return "N/A"

        val hours = minutes / 60
        val mins = minutes % 60
        return if (hours > 0) {
            "${hours}h ${mins}m"
        } else {
            "${mins}m"
        }
    }

    fun formatBoxOffice(amount: Long?): String {
        if (amount == null || amount <= 0) return "N/A"

        return when {
            amount >= 1_000_000_000 -> String.format("%.1fB", amount / 1_000_000_000.0)
            amount >= 1_000_000 -> String.format("%.1fM", amount / 1_000_000.0)
            amount >= 1_000 -> String.format("%.1fK", amount / 1_000.0)
            else -> amount.toString()
        }
    }

}