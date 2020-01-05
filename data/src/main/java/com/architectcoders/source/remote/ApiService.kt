package com.architectcoders.source.remote

import com.architectcoders.data.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?sort_by=popularity.desc")
   suspend fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String = BuildConfig.movie_db_api_key
    ): Response<MovieDbResult>
}
