package com.architectcoders.data


/**
 * Created by Gabriel Pozo Guzman on 2019-12-16.
 */

class ApiRepo(private val apiService: ApiService) {

    suspend fun getPopularMovies() =
        apiService
            .getPopularMoviesAsync()
            .await()
}
