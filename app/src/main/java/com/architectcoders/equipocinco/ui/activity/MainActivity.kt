package com.architectcoders.equipocinco.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.common.FragmentFrameHelper
import com.architectcoders.equipocinco.common.FragmentFrameHelper.Companion.INDEX_FAVOURITE
import com.architectcoders.equipocinco.common.FragmentFrameHelper.Companion.INDEX_POPULAR
import com.architectcoders.equipocinco.common.FragmentFrameHelper.Companion.INDEX_TOP_RATED
import com.architectcoders.equipocinco.extensions.newInstance
import com.architectcoders.equipocinco.ui.fragment.BaseFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.FavouriteMoviesFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.PopularMoviesFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.TopRatedMoviesFragment
import com.ncapdevi.fragnav.FragNavController

class MainActivity : AppCompatActivity(), FragNavController.RootFragmentListener,
    BaseFragment.FragmentNavigation {

    private val fragmentHelper = FragmentFrameHelper(this)
    private lateinit var activity: MainActivity
    override val numberOfRootFragments: Int = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        fragmentHelper.setupNavController(savedInstanceState)
        activity = this@MainActivity
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentHelper.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (fragmentHelper.popFragmentNot()) {
            super.onBackPressed()
        }
    }

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            INDEX_POPULAR -> { return PopularMoviesFragment.newInstance() }
            INDEX_TOP_RATED -> return TopRatedMoviesFragment.newInstance()
            INDEX_FAVOURITE -> return FavouriteMoviesFragment.newInstance()
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun pushFragment(fragment: Fragment, sharedElementList: List<Pair<View, String>>?) {
        fragmentHelper.pushFragment(fragment)
    }
}
