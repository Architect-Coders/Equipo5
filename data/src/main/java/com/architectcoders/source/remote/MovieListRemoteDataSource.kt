package com.architectcoders.source.remote

import com.architectcoders.data.Result

class MovieListRemoteDataSource constructor(private val service: ApiService) : BaseDataSource() {

    suspend fun fetchMovies(locationCode : String): Result<MovieDbResult> {
        return getResult { service.getPopularMoviesAsync(region = locationCode) }
    }

    suspend fun fetchMoviesBySearch(query: String): Result<MovieDbResult> {
        return getResult { service.searchMoviesAsync(query) }
    }
}
