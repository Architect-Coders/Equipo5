package com.architectcoders.equipocinco.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import javax.inject.Inject

class DetailMovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(DetailMovieViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPresentationComponent().inject(this)
    }

}
