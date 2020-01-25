package com.architectcoders.source.remote

import com.architectcoders.data.Result

class MovieListRemoteDataSource constructor(private val service: ApiService) : BaseDataSource() {

    suspend fun getPopularMovies(locationCode : String): Result<MovieDbResult> {
        return getResult { service.getPopularMoviesAsync(region = locationCode) }
    }

    suspend fun getTopRatedMovies(locationCode : String): Result<MovieDbResult> {
        return getResult { service.getTopRatedMoviesAsync(region = locationCode) }
    }

    suspend fun searchMovies(query: String): Result<MovieDbResult> {
        return getResult { service.searchMoviesAsync(query) }
    }
}
