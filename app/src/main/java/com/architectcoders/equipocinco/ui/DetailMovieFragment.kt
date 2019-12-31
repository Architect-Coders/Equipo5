package com.architectcoders.equipocinco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.equipocinco.R
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import javax.inject.Inject

class DetailMovieFragment : Fragment() {

    companion object {
        private const val DEFAULT_MOVIE_ID_VALUE = 0

        const val MOVIE_ID_KEY = "id"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private var movieId: Int = DEFAULT_MOVIE_ID_VALUE

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(DetailMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { bundle ->
            movieId = bundle.getInt(MOVIE_ID_KEY, DEFAULT_MOVIE_ID_VALUE)
        }
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

}
