package com.architectcoders.equipocinco.ui.fragment.master.child

import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment

class TopRatedMoviesFragment : MoviesFragment() {

    override fun onRequestMovies() {
        viewModel.onRequestTopRatedMovies()
    }
}
