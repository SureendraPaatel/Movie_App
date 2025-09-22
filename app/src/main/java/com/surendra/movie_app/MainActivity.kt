package com.surendra.movie_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.surendra.movie_app.navigation.MovieAppNavGraph
import com.surendra.movie_app.ui.theme.Movie_AppTheme
import com.surendra.presentation.viewModel.BookmarksViewModel
import com.surendra.presentation.viewModel.MovieDetailViewModel
import com.surendra.presentation.viewModel.MovieListViewModel


class MainActivity : ComponentActivity() {

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get ViewModels from DI
        val applicationComponent = (application as MovieApplication).applicationComponent
        movieListViewModel = applicationComponent.movieListViewModel()
        movieDetailViewModel = applicationComponent.movieDetailViewModel()
        bookmarksViewModel = applicationComponent.bookmarksViewModel()

        setContent {
            Movie_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MovieAppNavGraph(
                        navController = navController,
                        movieListViewModel = movieListViewModel,
                        movieDetailViewModel = movieDetailViewModel,
                        bookmarksViewModel = bookmarksViewModel
                    )
                }
            }
        }
    }
}