package com.surendra.movie_app.di.components

import androidx.navigation.NavHostController
import com.surendra.movie_app.MainActivity
import com.surendra.movie_app.di.module.NavigationModule
import com.surendra.movie_app.di.scope.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        NavigationModule::class
    ]
)
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance navController: NavHostController
        ): ActivityComponent

    }

    fun inject(mainActivity : MainActivity)
}