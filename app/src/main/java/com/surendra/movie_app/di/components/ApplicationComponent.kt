package com.surendra.movie_app.di.components

import com.surendra.movie_app.MovieApplication
import com.surendra.movie_app.di.module.ApplicationModule
import com.surendra.movie_app.di.module.DataModule
import com.surendra.movie_app.di.module.DomainModule
import com.surendra.movie_app.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        DomainModule::class,
        DataModule::class
    ]
)

interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MovieApplication): ApplicationComponent
    }

    fun activityComponentFactory(): ActivityComponent.Factory
}