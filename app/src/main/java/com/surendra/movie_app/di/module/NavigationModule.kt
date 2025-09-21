package com.surendra.movie_app.di.module

import androidx.navigation.NavController
import com.surendra.movie_app.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    @ActivityScope
    fun provideMovieAppNavigator(
        navController: NavController
    ): MovieAppNavigator = MovieAppNavigator(navController)
}