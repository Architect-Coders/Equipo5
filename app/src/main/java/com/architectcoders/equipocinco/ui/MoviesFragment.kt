package com.architectcoders.equipocinco.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.MovieViewModel
import javax.inject.Inject

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */


class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(MovieViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPresentationComponent().inject(this)
    }
}