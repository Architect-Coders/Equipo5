package com.architectcoders.equipocinco.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.ui.activity.MainActivity
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy
import kotlinx.android.synthetic.main.activity_main.*

class FragmentFrameHelper(private val activity: MainActivity) {
    companion object {
        const val INDEX_POPULAR = FragNavController.TAB1
        const val INDEX_TOP_RATED = FragNavController.TAB2
        const val INDEX_FAVOURITE = FragNavController.TAB3
    }

    private val fragNavController: FragNavController =
        FragNavController(activity.supportFragmentManager, R.id.navHostFragment)


    fun setupNavController(savedInstanceState: Bundle?) {
        fragNavController.apply {
            rootFragmentListener = activity
            createEager = true
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                }
            }

            defaultTransactionOptions = FragNavTransactionOptions.newBuilder().customAnimations(
                R.anim.slide_in_from_right,
                R.anim.slide_out_to_left,
                R.anim.slide_in_from_left,
                R.anim.slide_out_to_right
            ).build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH

            navigationStrategy = UniqueTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    activity.bottom_navigation_view.selectTabAtPosition(index)
                }
            })
        }

        fragNavController.initialize(INDEX_POPULAR, savedInstanceState)
        val initial = savedInstanceState == null
        if (initial) {
            activity.bottom_navigation_view.selectTabAtPosition(INDEX_POPULAR)
        }

        activity.bottom_navigation_view.setOnTabSelectListener({ tabId ->
            when (tabId) {
                R.id.menu_popular -> fragNavController.switchTab(INDEX_POPULAR)
                R.id.menu_top_rated -> fragNavController.switchTab(INDEX_TOP_RATED)
                R.id.menu_favourites -> fragNavController.switchTab(INDEX_FAVOURITE)
            }
        }, initial)

        activity.bottom_navigation_view.setOnTabReselectListener { fragNavController.clearStack() }


    }

    fun pushFragment(fragment: Fragment) {
        fragNavController.pushFragment(fragment)
    }

    fun onSaveInstanceState(outState: Bundle) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun popFragmentNot(): Boolean = fragNavController.popFragment().not()

}