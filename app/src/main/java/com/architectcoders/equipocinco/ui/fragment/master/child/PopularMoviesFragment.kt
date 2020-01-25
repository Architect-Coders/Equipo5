package com.architectcoders.equipocinco.ui.fragment.master.child

import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment

class PopularMoviesFragment : MoviesFragment() {

    override fun onRequestMovieList() {
        viewModel.onRequestPopularMovieList()
    }
}
