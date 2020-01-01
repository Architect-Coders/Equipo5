package com.architectcoders.equipocinco.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.architectcoders.data.Movie

import com.architectcoders.equipocinco.R
import com.architectcoders.generic.framework.extension.view.loadUrl
import com.architectcoders.generic.util.toast
import com.architectcoders.presentation.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_movies.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>("movie")
        movie?.let {
            setMoviewInfo(it)
        }
    }

    private fun setMoviewInfo(movie : Movie){
        iv.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        tvTitle.text = movie.title
        tvDescription.text = movie.overview
    }
}
