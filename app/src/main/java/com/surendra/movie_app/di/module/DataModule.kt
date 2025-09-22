package com.surendra.movie_app.di.module

import android.content.Context
import com.surendra.core.utils.ApiConstants
import com.surendra.data.api.MovieApiService
import com.surendra.data.database.MovieDao
import com.surendra.data.database.MovieDatabase
import com.surendra.data.repository.MovieRepositoryImpl
import com.surendra.data.source.local.MovieLocalDataSource
import com.surendra.data.source.remote.MovieRemoteDataSource
import com.surendra.domain.repository.MovieRepository
import com.surendra.movie_app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideMovieDatabase(context: Context): MovieDatabase {
        return MovieDatabase.getInstance(context)
    }

    @Provides
    @ApplicationScope
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    @ApplicationScope
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSource(movieDao)
    }

    @Provides
    @ApplicationScope
    fun provideMovieRemoteDataSource(apiService: MovieApiService): MovieRemoteDataSource {
        return MovieRemoteDataSource(apiService)
    }

    @Provides
    @ApplicationScope
    fun provideMovieRepository(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource, localDataSource)
    }
}