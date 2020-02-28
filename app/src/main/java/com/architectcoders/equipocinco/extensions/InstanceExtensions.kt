package com.architectcoders.equipocinco.extensions

import android.os.Bundle
import com.architectcoders.equipocinco.ui.fragment.detail.DetailMovieFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.FavouriteMoviesFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.PopularMoviesFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.TopRatedMoviesFragment

fun PopularMoviesFragment.Companion.newInstance(): PopularMoviesFragment = PopularMoviesFragment()

fun TopRatedMoviesFragment.Companion.newInstance(): TopRatedMoviesFragment = TopRatedMoviesFragment()

fun FavouriteMoviesFragment.Companion.newInstance(): FavouriteMoviesFragment = FavouriteMoviesFragment()

//Detail Movie instance
fun DetailMovieFragment.Companion.newInstance(id: Int): DetailMovieFragment {
    val args = Bundle()
    args.putInt(MOVIE_ID_KEY, id)
    val fragment = DetailMovieFragment()
    fragment.arguments = args
    return fragment

}




