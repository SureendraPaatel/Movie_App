package com.surendra.movie_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.surendra.presentation.ui.screen.bookmark.BookmarksScreen
import com.surendra.presentation.ui.screen.movieDetail.MovieDetailScreen
import com.surendra.presentation.ui.screen.movieList.MovieListScreen
import com.surendra.presentation.viewModel.BookmarksViewModel
import com.surendra.presentation.viewModel.MovieDetailViewModel
import com.surendra.presentation.viewModel.MovieListViewModel

@Composable
fun MovieAppNavGraph(
    navController: NavHostController,
    movieListViewModel: MovieListViewModel,
    movieDetailViewModel: MovieDetailViewModel,
    bookmarksViewModel: BookmarksViewModel
) {
    NavHost(
        navController = navController,
        startDestination = MovieAppDestinations.MOVIE_LIST_ROUTE
    ) {
        composable(MovieAppDestinations.MOVIE_LIST_ROUTE) {
            // MovieListScreen() - Will be implemented in presentation module
            MovieListScreen(
                viewModel = movieListViewModel,
                onMovieClick = { movie ->
                    navController.navigate("${MovieAppDestinations.MOVIE_DETAIL_ROUTE}/${movie.id}")
                },
                onBookmarksClick = {
                    navController.navigate(MovieAppDestinations.BOOKMARKS_ROUTE)
                }
            )
        }


        composable(
            route = MovieAppDestinations.MOVIE_DETAIL_ROUTE_WITH_ARGS,
            arguments = listOf(
                navArgument(MovieAppDestinations.MOVIE_ID_ARG) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString(MovieAppDestinations.MOVIE_ID_ARG) ?: ""
            // MovieDetailScreen(movieId = movieId) - Will be implemented in presentation module

            MovieDetailScreen(
                movieId = movieId,
                viewModel = movieDetailViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(MovieAppDestinations.BOOKMARKS_ROUTE) {
            // BookmarksScreen() - Will be implemented in presentation module

            BookmarksScreen(
                viewModel = bookmarksViewModel,
                onMovieClick = { movie ->
                    navController.navigate("${MovieAppDestinations.MOVIE_DETAIL_ROUTE}/${movie.id}")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }
    }
}