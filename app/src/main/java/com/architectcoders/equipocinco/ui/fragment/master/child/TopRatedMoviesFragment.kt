package com.architectcoders.equipocinco.ui.fragment.master.child

import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment

class TopRatedMoviesFragment : MoviesFragment() {

    companion object;

    override fun onRequestMovies() = viewModel.onRequestTopRatedMovies()
}
