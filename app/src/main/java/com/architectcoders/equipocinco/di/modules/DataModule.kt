package com.architectcoders.equipocinco.di.modules

import android.app.Application
import com.architectcoders.domain.MoviesRepository
import com.architectcoders.equipocinco.data.source.local.DeviceManager
import com.architectcoders.location.LocationRepository
import com.architectcoders.location.PermissionChecker
import com.architectcoders.repository.ApiRepo
import com.architectcoders.source.local.DeviceSource
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.source.local.LocationDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun getSessionManager(app: Application): DeviceSource {
        return DeviceManager(
            app
        )
    }

    @Provides
    fun getMoviesDao(app: Application): com.architectcoders.equipocinco.data.source.local.MovieDao {
        return com.architectcoders.equipocinco.data.source.local.MoviesRoomDatabase.getDatabase(app).moviesDao()
    }

    @Provides
    fun getLocationRepository(
        locationDataSource: LocationDataSource,
        permissionChecker: PermissionChecker
    ) = LocationRepository(locationDataSource, permissionChecker)

    @Provides
    fun getApiRepository(
        moviesListRemoteDataSource: com.architectcoders.equipocinco.data.source.remote.MovieListRemoteDataSource,
        movieLocalDataSource: LocalDataSource,
        sessionManager: DeviceSource,
        locationRepository: LocationRepository
    ): MoviesRepository =
        ApiRepo(
            moviesListRemoteDataSource,
            movieLocalDataSource,
            sessionManager,
            locationRepository
        )
}