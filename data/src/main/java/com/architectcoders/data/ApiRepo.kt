package com.architectcoders.data


class ApiRepo(private val apiService: ApiService) {

    suspend fun getPopularMovies() =
        apiService
            .getPopularMoviesAsync()
            .await()

    suspend fun searchMoviesAsync(query: String) =
        apiService
            .searchMoviesAsync(query)
            .await()
}
