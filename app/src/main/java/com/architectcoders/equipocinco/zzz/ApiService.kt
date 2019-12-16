package com.architectcoders.equipocinco.zzz

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String
    ): Deferred<MovieDbResult>
}
