package com.architectcoders.equipocinco.di.modules

import android.app.Activity
import com.architectcoders.data.ApiRepo
import com.architectcoders.data.SessionManager
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.source.local.MovieDao
import com.architectcoders.source.local.RoomDataSource
import com.architectcoders.source.remote.ApiService
import com.architectcoders.source.remote.MovieListRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MoviesModule(private val context: Activity) {

    @Provides
    fun getApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun getRemoteDataSource(apiService: ApiService): MovieListRemoteDataSource {
        return MovieListRemoteDataSource(apiService)
    }

    @Provides
    fun getLocalDataSource(moviesDao: MovieDao): LocalDataSource {
        return RoomDataSource(moviesDao)
    }

    @Provides
    fun getApiRepository(
        moviesListRemoteDataSource: MovieListRemoteDataSource,
        movieLocalDataSource: LocalDataSource,
        sessionManager: SessionManager
    ): ApiRepo {
        return ApiRepo(moviesListRemoteDataSource, movieLocalDataSource, sessionManager)
    }
}
