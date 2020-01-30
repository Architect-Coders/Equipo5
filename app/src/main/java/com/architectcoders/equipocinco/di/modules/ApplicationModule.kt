package com.architectcoders.equipocinco.di.modules

import android.app.Application
import com.architectcoders.data.BuildConfig
import com.architectcoders.equipocinco.data.location.AndroidPermissionChecker
import com.architectcoders.equipocinco.data.location.PlayServicesLocationDataSource
import com.architectcoders.location.PermissionChecker
import com.architectcoders.source.local.LocalDataSource
import com.architectcoders.source.local.LocationDataSource
import com.architectcoders.equipocinco.data.source.local.MovieDao
import com.architectcoders.equipocinco.data.source.local.RoomDataSource
import com.architectcoders.equipocinco.data.source.remote.ApiService
import com.architectcoders.equipocinco.data.source.remote.MovieListRemoteDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun getClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder
                .addInterceptor(loggingInterceptor)
        }
        clientBuilder
            .addNetworkInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("Content-Type", "application/json")
                chain.proceed(requestBuilder.build())
            }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun getRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    @Provides
    fun getApiClient(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun getRemoteDataSource(apiService: ApiService) = MovieListRemoteDataSource(apiService)

    @Provides
    fun getLocalDataSource(moviesDao: MovieDao): LocalDataSource = RoomDataSource(moviesDao)

    @Provides
    fun getPlayServicesLocationDataSource(app: Application): LocationDataSource =
        PlayServicesLocationDataSource(app)

    @Provides
    fun getAndroidPermissionChecker(app: Application): PermissionChecker =
        AndroidPermissionChecker(app)


}