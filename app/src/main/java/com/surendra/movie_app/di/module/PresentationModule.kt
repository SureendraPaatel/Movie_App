package com.surendra.movie_app.di.module

import com.surendra.domain.usecase.*
import com.surendra.movie_app.di.scope.ApplicationScope
import com.surendra.presentation.viewModel.*
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    @ApplicationScope
    fun provideMovieListViewModel(
        getMoviesUseCase: GetMovieUseCase,
        searchMoviesUseCase: SearchMoviesUseCase,
        sortMoviesUseCase: SortMoviesUseCase,
        bookmarkMovieUseCase: BookmarkMovieUseCase,
        removeBookmarkUseCase: RemoveBookmarkUseCase
    ): MovieListViewModel = MovieListViewModel(
        getMoviesUseCase,
        searchMoviesUseCase,
        sortMoviesUseCase,
        bookmarkMovieUseCase,
        removeBookmarkUseCase
    )

    @Provides
    @ApplicationScope
    fun provideMovieDetailViewModel(
        getMovieDetailsUseCase: GetMovieDetailsUseCase,
        bookmarkMovieUseCase: BookmarkMovieUseCase,
        removeBookmarkUseCase: RemoveBookmarkUseCase,
        isMovieBookmarkedUseCase: IsMovieBookmarkedUseCase
    ): MovieDetailViewModel = MovieDetailViewModel(
        getMovieDetailsUseCase,
        bookmarkMovieUseCase,
        removeBookmarkUseCase,
        isMovieBookmarkedUseCase
    )

    @Provides
    @ApplicationScope
    fun provideBookmarksViewModel(
        getBookmarkedMoviesUseCase: GetBookmarkedMoviesUseCase,
        removeBookmarkUseCase: RemoveBookmarkUseCase
    ): BookmarksViewModel = BookmarksViewModel(
        getBookmarkedMoviesUseCase,
        removeBookmarkUseCase
    )

}