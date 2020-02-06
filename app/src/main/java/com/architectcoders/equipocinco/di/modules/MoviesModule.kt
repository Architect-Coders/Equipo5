package com.architectcoders.equipocinco.di.modules

import android.app.Activity
import com.architectcoders.data.ApiRepo
import com.architectcoders.data.SessionManager
import com.architectcoders.equipocinco.data.AndroidPermissionChecker
import com.architectcoders.equipocinco.data.PlayServicesLocationDataSource
import com.architectcoders.location.LocationRepository
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.source.local.MovieDao
import com.architectcoders.source.local.RoomDataSource
import com.architectcoders.source.remote.ApiService
import com.architectcoders.source.remote.MovieListRemoteDataSource
import com.gabriel.usecases.*
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
    fun getPlayServicesLocationDataSource(): PlayServicesLocationDataSource {
        return PlayServicesLocationDataSource(context.application)
    }

    @Provides
    fun getAndroidPermissionChecker(): AndroidPermissionChecker {
        return AndroidPermissionChecker(context.application)
    }

    @Provides
    fun getLocationRepository(
        locationDataSource: PlayServicesLocationDataSource,
        permissionChecker: AndroidPermissionChecker
    ): LocationRepository {
        return LocationRepository(locationDataSource, permissionChecker)
    }

    @Provides
    fun getPopularMoviesUseCase(moviesRepository: ApiRepo): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(moviesRepository)
    }

    @Provides
    fun getTopRatedMoviesUseCase(moviesRepository: ApiRepo): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(moviesRepository)
    }

    @Provides
    fun getSearchMoviesUseCase(moviesRepository: ApiRepo): GetSearchMoviesUseCase {
        return GetSearchMoviesUseCase(moviesRepository)
    }

    @Provides
    fun getMovieUseCase(moviesRepository: ApiRepo): GetMovieUseCase {
        return GetMovieUseCase(moviesRepository)
    }

    @Provides
    fun getFavoriteMoviesUseCase(moviesRepository: ApiRepo): GetFavoriteMoviesUseCase {
        return GetFavoriteMoviesUseCase(moviesRepository)
    }

    @Provides
    fun getApiRepository(
        moviesListRemoteDataSource: MovieListRemoteDataSource,
        movieLocalDataSource: LocalDataSource,
        sessionManager: SessionManager,
        locationRepository: LocationRepository
    ): ApiRepo {
        return ApiRepo(
            moviesListRemoteDataSource,
            movieLocalDataSource,
            sessionManager,
            locationRepository
        )
    }
}
