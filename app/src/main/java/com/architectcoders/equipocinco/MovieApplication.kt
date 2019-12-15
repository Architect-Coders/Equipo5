package com.architectcoders.equipocinco

import android.app.Application
import com.architectcoders.equipocinco.di.ApplicationComponent
import com.architectcoders.equipocinco.di.DaggerApplicationComponent
import com.architectcoders.equipocinco.di.modules.ApplicationModule

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */

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