package com.architectcoders.equipocinco.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.architectcoders.equipocinco.R
import com.architectcoders.generic.util.toast
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.architectcoders.presentation.viewmodels.MovieViewModel.UiModel
import com.architectcoders.Movie
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(MovieViewModel::class.java)
    }

    private lateinit var activity: MainActivity
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        activity = this@MainActivity
        getPresentationComponent().inject(this)

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(model: UiModel) {
        when (model) {
            is UiModel.RequestMovies -> viewModel.onRequestMovieList()
            is UiModel.Content -> updateData(model.movies)
        }
    }

    private fun updateData(movies: List<Movie>) {
        initAdapter(movies)
    }

    private fun initAdapter(items: List<Movie>) {
        rv?.let {
            rv.layoutManager = GridLayoutManager(activity, 3)
            adapter = MovieAdapter(items.toMutableList()) {
                toast("${it.originalTitle}")
            }
            rv.adapter = adapter
        }
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
}
