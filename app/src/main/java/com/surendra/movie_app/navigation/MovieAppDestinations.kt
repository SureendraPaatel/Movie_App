package com.surendra.movie_app.navigation

object MovieAppDestinations {
    const val MOVIE_LIST_ROUTE = "movie_list"
    const val MOVIE_DETAIL_ROUTE = "movie_detail"
    const val BOOKMARKS_ROUTE = "bookmarks"

    // Arguments
    const val MOVIE_ID_ARG = "movieId"

    // Routes with arguments
    const val MOVIE_DETAIL_ROUTE_WITH_ARGS = "$MOVIE_DETAIL_ROUTE/{$MOVIE_ID_ARG}"
}