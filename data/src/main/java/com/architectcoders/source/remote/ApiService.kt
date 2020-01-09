package com.architectcoders.source.remote

import com.architectcoders.data.BuildConfig
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val QUERY = "query"
    }

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String = BuildConfig.movie_db_api_key
    ): Deferred<MovieDbResult>


    @GET("search/movie")
    fun searchMoviesAsync(
        @Query(QUERY) query: String,
        @Query("api_key") apiKey: String = BuildConfig.movie_db_api_key
    ): Deferred<MovieDbResult>
}
