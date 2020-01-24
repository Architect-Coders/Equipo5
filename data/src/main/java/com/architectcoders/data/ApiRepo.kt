package com.architectcoders.data

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.state.DataState
import com.architectcoders.location.LocationRepository
import com.architectcoders.mappers.mapServerMovieToDomain
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.source.remote.MovieListRemoteDataSource


class ApiRepo(
    private val remoteDataSource: MovieListRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sessionManager: SessionManager,
    private val locationRepository: LocationRepository
) : MoviesRepository {
    override suspend fun getPopularMovies() = singleSourceOfData(
        databaseQuery = { localDataSource.getPopularMovies(locationRepository.getLastLocation()) },
        networkCall = { remoteDataSource.fetchMovies(locationRepository.getLastLocation()) },
        saveCallResult = { localDataSource.saveMovies(it.results.map(mapServerMovieToDomain)) },
        shouldFetch = { (sessionManager.isConnectedToTheInternet()) })

    override suspend fun searchMovies(query: String) = singleSourceOfData(
        databaseQuery = { localDataSource.getPopularMoviesBySearch(query) },
        networkCall = { remoteDataSource.fetchMoviesBySearch(query) },
        saveCallResult = { localDataSource.saveMovies(it.results.map(mapServerMovieToDomain)) },
        shouldFetch = { (sessionManager.isConnectedToTheInternet()) })

    override suspend fun getMovieById(id: Int) = DataState.Success(localDataSource.getMovie(id))
}
