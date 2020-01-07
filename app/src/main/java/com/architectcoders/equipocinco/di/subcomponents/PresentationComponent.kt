package com.architectcoders.equipocinco.di.subcomponents

import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.modules.MoviesScope
import com.architectcoders.equipocinco.ui.DetailMovieFragment
import com.architectcoders.equipocinco.ui.MoviesFragment
import com.architectcoders.equipocinco.ui.MainActivity
import com.architectcoders.presentation.di.modules.MoviesViewModelModule
import dagger.Subcomponent

@MoviesScope
@Subcomponent(modules = [MoviesViewModelModule::class, MoviesModule::class])
interface PresentationComponent {
    fun inject(moviesFragment: MoviesFragment)

    fun inject(detailMovieFragment: DetailMovieFragment)

    fun inject(mainActivity: MainActivity)
}
