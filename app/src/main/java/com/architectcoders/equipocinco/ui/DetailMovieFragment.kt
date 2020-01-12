package com.architectcoders.equipocinco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.Movie
import com.architectcoders.equipocinco.R
import com.architectcoders.generic.framework.extension.view.loadUrl
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel.UiModel.Loading
import kotlinx.android.synthetic.main.fragment_detail_movie.*

class DetailMovieFragment : Fragment() {

    companion object {
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/original/"
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
            val movie = bundle.getParcelable<Movie>(MOVIE_ID_KEY)
            viewModel.onMovieDetailLoading(movie)
            viewModel.model.observe(this, Observer(::refresh))
        }
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    private fun refresh(model: DetailMovieViewModel.UiModel) {
        when (model) {
            is Loading -> updateUI(model.movie)
        }
    }

    private fun updateUI(movie: Movie) {
        ivMoviePoster.loadUrl("$POSTER_BASE_URL${movie.posterPath}")
        tvTitle.text = movie.title
        tvDescription.text = movie.overview
    }

}
