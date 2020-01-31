package com.architectcoders.equipocinco.ui.activity

import androidx.fragment.app.Fragment
import com.architectcoders.equipocinco.MovieApplication
import com.architectcoders.equipocinco.di.ApplicationComponent
import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.subcomponents.PresentationComponent

abstract class BaseFragment : Fragment() {
    private var mIsInjectorUsed: Boolean = false

    protected fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("there is no need to use injector more than once")
        }
        mIsInjectorUsed = true
        return getApplicationComponent().presentationComponent(MoviesModule(activity!!))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (activity?.application as MovieApplication).applicationComponent
    }
}
