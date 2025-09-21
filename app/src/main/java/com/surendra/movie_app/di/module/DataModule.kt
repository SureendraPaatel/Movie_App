package com.surendra.movie_app.di.module

import android.content.Context
import com.surendra.domain.repository.MovieRepository
import com.surendra.movie_app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    // Note: These will be implemented when you create the data module
    // For now, we'll add placeholder comments

    /*

    @Provides
    @ApplicationScope
    fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository = movieRepositoryImpl

    @Provides
    @ApplicationScope
    fun provideMovieApiService(
        retrofit: Retrofit
    ): MovieApiService = retrofit.create(MovieApiService::class.java)

    @Provides
    @ApplicationScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://68cc08ab716562cf507620db.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideMovieDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @ApplicationScope
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
    */
}