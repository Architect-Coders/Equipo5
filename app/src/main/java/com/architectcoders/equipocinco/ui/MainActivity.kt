package com.architectcoders.equipocinco.ui

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.architectcoders.equipocinco.R
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        activity = this@MainActivity
        setUpNavigation()

        getPresentationComponent().inject(this)
    }


    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottom_navigation_view,
            navHostFragment!!.navController
        )
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
}
