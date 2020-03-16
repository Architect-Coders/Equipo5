package com.architectcoders.equipocinco.ui.fragment.master.child

import com.architectcoders.equipocinco.ui.fragment.master.MoviesFragment

class FavouriteMoviesFragment : MoviesFragment(){

    override fun onRequestMovies() = viewModel.onRequestFavoriteMovies()
    
}
