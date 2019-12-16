package com.architectcoders.equipocinco.di.modules

import android.app.Activity
import com.architectcoders.data.ApiRepo
import com.architectcoders.data.ApiService
import com.architectcoders.equipocinco.zzz.ApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */

@Module
class MoviesModule(private val context: Activity) {

    @Provides
    fun getApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun getRegionRepository(apiService: ApiService): ApiRepo {
        return ApiRepo(apiService)
    }
}
