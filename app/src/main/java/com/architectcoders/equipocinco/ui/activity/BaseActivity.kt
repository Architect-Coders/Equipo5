package com.architectcoders.equipocinco.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.equipocinco.MovieApplication
import com.architectcoders.equipocinco.di.ApplicationComponent
import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.di.subcomponents.PresentationComponent

abstract class BaseActivity : AppCompatActivity() {
    private var mIsInjectorUsed: Boolean = false

    protected fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("there is no need to use injector more than once")
        }
        mIsInjectorUsed = true
        return getApplicationComponent().presentationComponent(MoviesModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as MovieApplication).applicationComponent
    }
}
