package com.architectcoders.equipocinco

import android.app.Application
import com.architectcoders.equipocinco.di.ApplicationComponent
import com.architectcoders.equipocinco.di.DaggerApplicationComponent
import com.architectcoders.equipocinco.di.modules.ApplicationModule

class MovieApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
    }
}