package com.architectcoders.equipocinco.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.extensions.newInstance
import com.architectcoders.equipocinco.ui.fragment.BaseFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.FavouriteMoviesFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.PopularMoviesFragment
import com.architectcoders.equipocinco.ui.fragment.master.child.TopRatedMoviesFragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragNavController.RootFragmentListener,FragNavController.TransactionListener,
    BaseFragment.FragmentNavigation{

    private val fragNavController: FragNavController =
        FragNavController(supportFragmentManager, R.id.navHostFragment)

    private lateinit var activity: MainActivity
     override val numberOfRootFragments: Int = 3



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        activity = this@MainActivity

        /**
         *ON CREATE
         */

        fragNavController.apply {
            rootFragmentListener = this@MainActivity
            createEager = true
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                }
            }

            defaultTransactionOptions = FragNavTransactionOptions.newBuilder().customAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_left, R.anim.slide_out_to_right).build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH

            navigationStrategy = UniqueTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    bottom_navigation_view.selectTabAtPosition(index)
                }
            })
        }

        fragNavController.initialize(INDEX_POPULAR, savedInstanceState)

        val initial = savedInstanceState == null
        if (initial) {
            bottom_navigation_view.selectTabAtPosition(INDEX_POPULAR)
        }

        bottom_navigation_view.setOnTabSelectListener({ tabId ->
            when (tabId) {
                R.id.menu_popular -> fragNavController.switchTab(INDEX_POPULAR)
                R.id.menu_top_rated -> fragNavController.switchTab(INDEX_TOP_RATED)
                R.id.menu_favourites -> fragNavController.switchTab(INDEX_FAVOURITE)
            }
        }, initial)

        bottom_navigation_view.setOnTabReselectListener { fragNavController.clearStack() }

        /**
         *
         *
         */
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragNavController.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (fragNavController.popFragment().not()) {
            super.onBackPressed()
        }
    }

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            INDEX_POPULAR -> return PopularMoviesFragment.newInstance()
            INDEX_TOP_RATED -> return TopRatedMoviesFragment.newInstance()
            INDEX_FAVOURITE -> return FavouriteMoviesFragment.newInstance()
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun pushFragment(fragment: Fragment, sharedElementList: List<Pair<View, String>>?) {
        val options = FragNavTransactionOptions.newBuilder()
        options.enterAnimation = R.anim.slide_in_up
        fragNavController.pushFragment(fragment, options.build())
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {
        supportActionBar?.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())

    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())
    }
}

const val INDEX_POPULAR = FragNavController.TAB1
const val INDEX_TOP_RATED = FragNavController.TAB2
const val INDEX_FAVOURITE = FragNavController.TAB3
