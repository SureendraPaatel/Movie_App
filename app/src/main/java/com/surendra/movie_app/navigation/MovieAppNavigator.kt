package com.surendra.movie_app.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

class MovieAppNavigator @Inject constructor(
    private val navController: NavHostController
) {

    fun navigateToMovieDetail(movieId : String) {
        navController.navigate("${MovieAppDestinations.MOVIE_DETAIL_ROUTE}/$movieId")
    }

    fun navigateToBookmarks(){
        navController.navigate(MovieAppDestinations.BOOKMARKS_ROUTE)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToMovieList() {
        navController.navigate(MovieAppDestinations.MOVIE_LIST_ROUTE) {
            popUpTo(MovieAppDestinations.MOVIE_LIST_ROUTE) {
                inclusive = true
            }
        }
    }

}
