package com.surendra.data.mapper

import com.surendra.data.api.model.MovieApiResponse
import com.surendra.data.database.MovieEntity
import com.surendra.domain.model.Movie
import com.surendra.domain.model.MovieDetails

object MovieMapper {

    fun mapApiResponseToDomain(apiResponse: MovieApiResponse): Movie{
        return Movie (
            id = apiResponse.id,
            title = apiResponse.title,
            releaseDate = apiResponse.releaseDate,
            rating = apiResponse.rating,
            poster = apiResponse.poster,
            overview = apiResponse.overview,
            genre = apiResponse.genre,
            director = apiResponse.director,
            cast = apiResponse.cast,
            duration = apiResponse.duration,
            isBookmarked = false
        )

    }



    fun mapApiResponseToMovieDetails(apiResponse: MovieApiResponse): MovieDetails {
        return MovieDetails(
            id = apiResponse.id,
            title = apiResponse.title,
            releaseDate = apiResponse.releaseDate,
            rating = apiResponse.rating,
            poster = apiResponse.poster,
            overview = apiResponse.overview,
            genre = apiResponse.genre,
            director = apiResponse.director,
            cast = apiResponse.cast,
            duration = apiResponse.duration,
            isBookmarked = false // Will be updated based on local DB
        )
    }

    fun mapDomainToEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            title = movie.title,
            releaseDate = movie.releaseDate,
            rating = movie.rating,
            poster = movie.poster,
            overview = movie.overview,
            genre = movie.genre,
            director = movie.director,
            cast = movie.cast,
            duration = movie.duration
        )
    }

    fun mapEntityToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            releaseDate = entity.releaseDate,
            rating = entity.rating,
            poster = entity.poster,
            overview = entity.overview,
            genre = entity.genre,
            director = entity.director,
            cast = entity.cast,
            duration = entity.duration,
            isBookmarked = true
        )
    }
}