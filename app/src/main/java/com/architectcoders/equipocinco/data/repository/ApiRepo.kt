package com.architectcoders.equipocinco.data.repository

import com.architectcoders.equipocinco.data.source.SessionManager
import com.architectcoders.data.singleSourceOfData
import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.state.DataState
import com.architectcoders.equipocinco.data.source.remote.MovieListRemoteDataSource
import com.architectcoders.location.LocationRepository
import com.architectcoders.source.local.LocalDataSource


class ApiRepo(
    private val remoteDataSource: MovieListRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sessionManager: SessionManager,
    private val locationRepository: LocationRepository
) : MoviesRepository {
    override suspend fun getPopularMovies() =
        singleSourceOfData(
            databaseQuery = { localDataSource.getPopularMovies(locationRepository.getLastLocation()) },
            networkCall = { remoteDataSource.fetchMovies(locationRepository.getLastLocation()) },
            saveCallResult = { localDataSource.saveMovies(it) },
            shouldFetch = { (sessionManager.isConnectedToTheInternet()) })

    override suspend fun searchMovies(query: String) =
        singleSourceOfData(
            databaseQuery = { localDataSource.getPopularMoviesBySearch(query) },
            networkCall = { remoteDataSource.fetchMoviesBySearch(query) },
            saveCallResult = { localDataSource.saveMovies(it) },
            shouldFetch = { (sessionManager.isConnectedToTheInternet()) })

    override suspend fun getMovieById(id: Int) = DataState.Success(localDataSource.getMovie(id))
}
