package com.architectcoders.equipocinco.di

import com.architectcoders.equipocinco.di.modules.ApplicationModule
import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.subcomponents.PresentationComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun presentationComponent(moviesModule: MoviesModule): PresentationComponent
}