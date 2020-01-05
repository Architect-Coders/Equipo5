package com.architectcoders.data

import com.architectcoders.data.FlagSourceData.*
import com.architectcoders.mappers.mapRemoteMovieToDb
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.Movie
import com.architectcoders.source.remote.MovieListRemoteDataSource


class ApiRepo(
    private val remoteDataSource: MovieListRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sessionManager: SessionManager
) {
    suspend fun getPopularMovies(): DataState<List<Movie>> = singleSourceOfData(
        databaseQuery = { localDataSource.getPopularMovies() },
        networkCall = { remoteDataSource.fetchMovies() },
        saveCallResult = { localDataSource.saveMovies(it.results.map(mapRemoteMovieToDb)) },
        shouldFetch = { if (sessionManager.isConnectedToTheInternet()) FETCH_FROM_NETWORK else NO_NETWORK_CONNECTION })
}
