package com.architectcoders.equipocinco.ui.fragment.master.child

import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment

class PopularMoviesFragment : MoviesFragment() {

    companion object;

    override fun onRequestMovies() = viewModel.onRequestPopularMovies()
}