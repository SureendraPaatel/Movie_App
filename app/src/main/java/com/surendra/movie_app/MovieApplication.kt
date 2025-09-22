package com.surendra.movie_app


import android.app.Application
import com.surendra.movie_app.di.components.ApplicationComponent
import com.surendra.movie_app.di.components.DaggerApplicationComponent

class MovieApplication : Application() {

    // Application-wide component
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MovieApplication
            private set
    }
}
