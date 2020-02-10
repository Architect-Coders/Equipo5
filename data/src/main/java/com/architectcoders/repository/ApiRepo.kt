package com.architectcoders.repository

import com.architectcoders.data.singleSourceOfData
import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.state.DataState
import com.architectcoders.location.LocationRepository
import com.architectcoders.source.local.DeviceSource
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.source.remote.RemoteDataSource


class ApiRepo(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val deviceManager: DeviceSource,
    private val locationRepository: LocationRepository
) : MoviesRepository {
    override suspend fun getPopularMovies() =
        singleSourceOfData(
            databaseQuery = { localDataSource.getPopularMovies(locationRepository.getLastLocation()) },
            networkCall = { remoteDataSource.fetchMovies(locationRepository.getLastLocation()) },
            saveCallResult = { localDataSource.saveMovies(it) },
            shouldFetch = { (deviceManager.isConnectedToTheInternet()) })

    override suspend fun getTopRatedMovies() = singleSourceOfData(
        databaseQuery = { localDataSource.getTopRatedMovies(locationRepository.getLastLocation()) },
        networkCall = { remoteDataSource.getTopRatedMovies(locationRepository.getLastLocation()) },
        saveCallResult = { localDataSource.saveMovies(it) },
        shouldFetch = { (deviceManager.isConnectedToTheInternet()) })

    override suspend fun searchMovies(query: String) =
        singleSourceOfData(
            databaseQuery = { localDataSource.searchMovies(query) },
            networkCall = { remoteDataSource.fetchMoviesBySearch(query) },
            saveCallResult = { localDataSource.saveMovies(it) },
            shouldFetch = { (deviceManager.isConnectedToTheInternet()) })

    override suspend fun getMovieById(id: Int) = DataState.Success(localDataSource.getMovie(id))
}
