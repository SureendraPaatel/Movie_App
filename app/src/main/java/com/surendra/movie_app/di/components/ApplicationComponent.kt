package com.surendra.movie_app.di.components

import com.surendra.movie_app.di.components.DaggerApplicationComponent
import com.surendra.movie_app.MovieApplication
import com.surendra.movie_app.di.module.ApplicationModule
import com.surendra.movie_app.di.module.DataModule
import com.surendra.movie_app.di.module.DomainModule
import com.surendra.movie_app.di.module.PresentationModule
import com.surendra.movie_app.di.scope.ApplicationScope
import com.surendra.presentation.viewModel.BookmarksViewModel
import com.surendra.presentation.viewModel.MovieDetailViewModel
import com.surendra.presentation.viewModel.MovieListViewModel
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        DomainModule::class,
        DataModule::class,
        PresentationModule::class
    ]
)

interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MovieApplication): ApplicationComponent
    }

    fun activityComponentFactory(): ActivityComponent.Factory

    // Expose ViewModels so they can be retrieved from the graph
    fun movieListViewModel(): MovieListViewModel
    fun movieDetailViewModel(): MovieDetailViewModel
    fun bookmarksViewModel(): BookmarksViewModel
}