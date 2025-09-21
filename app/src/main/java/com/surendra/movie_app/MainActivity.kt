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
import com.surendra.movie_app.navigation.MovieAppNavigator
import com.surendra.movie_app.ui.theme.Movie_AppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: MovieAppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = rememberNavController()

        // Inject dependencies
        (application as MovieApplication)
            .applicationComponent
            .activityComponentFactory()
            .create(navController)
            .inject(this)

        setContent {
            Movie_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieAppNavGraph(navController = navController)
                }
            }
        }
    }
}