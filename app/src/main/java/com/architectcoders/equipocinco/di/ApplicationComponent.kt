package com.architectcoders.equipocinco.di

import android.app.Application
import com.architectcoders.equipocinco.di.modules.ApplicationModule
import com.architectcoders.equipocinco.di.modules.DataModule
import com.architectcoders.equipocinco.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {

    fun plus(module: PopularMoviesModule): PopularMoviesComponent
    fun plus(module: DetailMovieModule): DetailMovieComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }
}