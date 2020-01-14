package com.architectcoders.equipocinco

import android.app.Application
import com.architectcoders.di.ApplicationDatabaseModule
import com.architectcoders.equipocinco.di.ApplicationComponent
import com.architectcoders.equipocinco.di.DaggerApplicationComponent
import com.architectcoders.generic.util.KLog

class MovieApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        KLog.launch(BuildConfig.DEBUG)
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationDatabaseModule(ApplicationDatabaseModule(this)).build()
    }
}
