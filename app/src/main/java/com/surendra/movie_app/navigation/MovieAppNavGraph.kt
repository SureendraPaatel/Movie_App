package com.surendra.movie_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun MovieAppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MovieAppDestinations.MOVIE_LIST_ROUTE
    ) {
        composable(MovieAppDestinations.MOVIE_LIST_ROUTE) {
            // MovieListScreen() - Will be implemented in presentation module
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
        }

        composable(MovieAppDestinations.BOOKMARKS_ROUTE) {
            // BookmarksScreen() - Will be implemented in presentation module
        }
    }
}