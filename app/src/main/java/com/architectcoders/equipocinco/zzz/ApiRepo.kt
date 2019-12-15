package com.architectcoders.equipocinco.zzz

import android.app.Activity
import com.architectcoders.equipocinco.R

class ApiRepo(activity: Activity) {

    private val apiService = ApiClient.get().create(ApiService::class.java)
    private val apiKey = activity.getString(R.string.movie_db_api_key)

    suspend fun getPopularMovies() =
        apiService
            .getPopularMoviesAsync(
                apiKey
            )
            .await()
}
