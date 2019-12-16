package com.architectcoders.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String = BuildConfig.movie_db_api_key
    ): Deferred<MovieDbResult>
}
