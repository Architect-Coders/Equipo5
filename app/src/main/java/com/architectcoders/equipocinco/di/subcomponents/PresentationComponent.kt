package com.architectcoders.equipocinco.di.subcomponents

import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.modules.MoviesScope
import com.architectcoders.equipocinco.ui.fragment.detail.DetailMovieFragment
import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment
import com.architectcoders.equipocinco.ui.activity.MainActivity
import com.architectcoders.presentation.di.modules.MoviesViewModelModule
import dagger.Subcomponent

@MoviesScope
@Subcomponent(modules = [MoviesViewModelModule::class, MoviesModule::class])
interface PresentationComponent {
    fun inject(moviesFragment: MoviesFragment)

    fun inject(detailMovieFragment: DetailMovieFragment)

    fun inject(mainActivity: MainActivity)
}
