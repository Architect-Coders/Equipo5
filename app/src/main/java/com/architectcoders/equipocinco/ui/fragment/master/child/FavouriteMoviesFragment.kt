package com.architectcoders.equipocinco.ui.fragment.master.child

import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment

class FavouriteMoviesFragment : MoviesFragment(){

    override fun onRequestMovies() = viewModel.onRequestFavoriteMovies()

    override fun onResume() {
        super.onResume()
        onRequestMovies()
    }
}
