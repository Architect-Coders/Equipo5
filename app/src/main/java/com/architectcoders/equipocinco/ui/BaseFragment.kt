package com.architectcoders.equipocinco.ui

import androidx.fragment.app.Fragment
import com.architectcoders.equipocinco.MovieApplication
import com.architectcoders.equipocinco.di.ApplicationComponent
import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.subcomponents.PresentationComponent

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */

abstract class BaseFragment : Fragment() {

    protected fun getPresentationComponent(): PresentationComponent {
        return activity?.run {
            getApplicationComponent().presentationComponent(MoviesModule(this))
        } ?: throw Exception("Exception: Not able to retrieve the activity")

    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (activity?.application as MovieApplication).applicationComponent
    }
}