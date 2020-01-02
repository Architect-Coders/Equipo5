package com.architectcoders.equipocinco.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.architectcoders.data.Movie
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.ui.DetailMovieFragment.Companion.MOVIE_ID_KEY
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.architectcoders.presentation.viewmodels.MovieViewModel.UiModel
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
    private lateinit var navController: NavController
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        activity = this@MainActivity
        navController = getNavController()
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
            adapter = MovieAdapter(items.toMutableList()) { movie ->
                navigateToDetailMovie(movie.id)
            }
            rv.adapter = adapter
        }
    }

    private fun navigateToDetailMovie(id: Int) =
        navController.navigate(
            R.id.action_moviesFragment_to_detailMovieFragment,
            bundleOf(MOVIE_ID_KEY to id)
        )

    private fun getNavController() =
        Navigation.findNavController(this, R.id.navHostFragment)

    override fun onSupportNavigateUp() =
        navController.navigateUp()
}
