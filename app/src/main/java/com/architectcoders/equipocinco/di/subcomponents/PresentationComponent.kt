package com.architectcoders.equipocinco.di.subcomponents

import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.modules.MoviesScope
import com.architectcoders.equipocinco.ui.MainActivity
import com.architectcoders.presentation.di.modules.MoviesViewModelModule
import dagger.Subcomponent

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */

@MoviesScope
@Subcomponent(modules = [MoviesViewModelModule::class, MoviesModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
}