package com.architectcoders.source.remote

import com.architectcoders.data.Result

class MovieListRemoteDataSource constructor(private val service: ApiService) : BaseDataSource() {

    suspend fun fetchMovies(): Result<MovieDbResult> {
        return getResult { service.getPopularMoviesAsync() }
    }

    suspend fun fetchMovies(query: String): Result<MovieDbResult> {
        return getResult { service.searchMoviesAsync(query) }
    }
}