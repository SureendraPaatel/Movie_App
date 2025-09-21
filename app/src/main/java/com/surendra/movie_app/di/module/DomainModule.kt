package com.surendra.movie_app.di.module

import com.surendra.domain.repository.MovieRepository
import com.surendra.domain.usecase.BookmarkMovieUseCase
import com.surendra.domain.usecase.GetBookmarkedMoviesUseCase
import com.surendra.domain.usecase.GetMovieDetailsUseCase
import com.surendra.domain.usecase.GetMovieUseCase
import com.surendra.domain.usecase.IsMovieBookmarkedUseCase
import com.surendra.domain.usecase.RemoveBookmarkUseCase
import com.surendra.domain.usecase.SearchMoviesUseCase
import com.surendra.domain.usecase.SortMoviesUseCase
import com.surendra.movie_app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    @ApplicationScope
    fun provideGetMoviesUseCase(
        movieRepository: MovieRepository
    ): GetMovieUseCase = GetMovieUseCase(movieRepository)

    @Provides
    @ApplicationScope
    fun provideGetMovieDetailsUseCase(
        movieRepository: MovieRepository
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)

    @Provides
    @ApplicationScope
    fun provideSearchMovieUseCase(
        movieRepository: MovieRepository
    ): SearchMoviesUseCase = SearchMoviesUseCase(movieRepository)

    @Provides
    @ApplicationScope
    fun provideSortMoviesUseCase(
        movieRepository: MovieRepository
    ): SortMoviesUseCase = SortMoviesUseCase(movieRepository)


    @Provides
    @ApplicationScope
    fun provideGetBookmarkedMoviesUseCase(
        movieRepository: MovieRepository
    ): GetBookmarkedMoviesUseCase = GetBookmarkedMoviesUseCase(movieRepository)

    @Provides
    @ApplicationScope
    fun provideBookmarkMovieUseCase(
        movieRepository: MovieRepository
    ): BookmarkMovieUseCase = BookmarkMovieUseCase(movieRepository)

    @Provides
    @ApplicationScope
    fun provideRemoveBookmarkUseCase(
        movieRepository: MovieRepository
    ): RemoveBookmarkUseCase = RemoveBookmarkUseCase(movieRepository)

    @Provides
    @ApplicationScope
    fun provideIsMovieBookmarkedUseCase(
        movieRepository: MovieRepository
    ): IsMovieBookmarkedUseCase = IsMovieBookmarkedUseCase(movieRepository)
}