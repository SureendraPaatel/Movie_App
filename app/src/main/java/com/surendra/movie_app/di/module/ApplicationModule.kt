package com.surendra.movie_app.di.module

import android.content.Context
import com.surendra.movie_app.MovieApplication
import com.surendra.movie_app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: MovieApplication) : Context = application.applicationContext
}