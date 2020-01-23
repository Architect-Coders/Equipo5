package com.architectcoders.equipocinco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.domain.model.Movie
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.extensions.getOriginalTitle
import com.architectcoders.equipocinco.extensions.getPopularity
import com.architectcoders.equipocinco.extensions.getReleaseDateFormatted
import com.architectcoders.equipocinco.extensions.getVoteAverage
import com.architectcoders.generic.framework.extension.view.loadUrl
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*

class DetailMovieFragment : Fragment() {

    companion object {
        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/original/"
        const val MOVIE_ID_KEY = "DetailMovieFragment::id"

    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            (activity as MainActivity).viewModelFactory
        ).get(DetailMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { bundle ->
            bundle.getInt(MOVIE_ID_KEY)?.let {
                viewModel.onMovieDetailLoading(it)
            }
            viewModel.model.observe(this, Observer(::refresh))
        }
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    private fun refresh(model: DetailMovieViewModel.UiModel) {
        when (model) {
            is DetailMovieViewModel.UiModel.Content -> updateUI(model.movie)
        }
    }

    private fun updateUI(movie: Movie) {
        movie.run {
            ivMoviePoster.loadUrl("$POSTER_BASE_URL${posterPath}")
            tvTitle.text = title
            tvOriginalTitle.text = getOriginalTitle()
            tvPopularity.text = getPopularity()
            tvRateNumber.text = getVoteAverage()
            tvReleaseDate.text = getReleaseDateFormatted()
            tvDescription.text = overview
        }
    }

}
