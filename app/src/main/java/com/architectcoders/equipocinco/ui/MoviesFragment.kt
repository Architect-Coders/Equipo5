package com.architectcoders.equipocinco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.architectcoders.Movie
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.framework.SearchManager
import com.architectcoders.generic.framework.extension.isFilled
import com.architectcoders.generic.framework.extension.view.setVisibleOrGone
import com.architectcoders.presentation.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.progress_bar.*
import kotlinx.android.synthetic.main.search.*

class MoviesFragment : Fragment() {

    private lateinit var navController: NavController

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            (activity as MainActivity).viewModelFactory
        ).get(MovieViewModel::class.java)
    }

    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUI))

        initClSearch()
    }

    private fun updateUI(model: MovieViewModel.UiModel) {
        when (model) {
            is MovieViewModel.UiModel.RequestMovies -> viewModel.onRequestMovieList()
            is MovieViewModel.UiModel.Content -> updateData(model.movies)
        }
    }

    private fun updateData(movies: List<Movie>) {
        initAdapter(movies)
    }

    private fun initAdapter(items: List<Movie>) {
        rv?.let {
            rv.layoutManager = GridLayoutManager(activity, 3)
            adapter = MovieAdapter(items.toMutableList()) {
                navController.navigate(
                    R.id.action_moviesFragment_to_detailMovieFragment,
                    bundleOf(DetailMovieFragment.MOVIE_ID_KEY to it)
                )
            }
            rv.adapter = adapter
            pb.hide()
        }
    }

    private fun initClSearch() {
        SearchManager(et, ib, object : SearchManager.Listener {
            override fun onTextChanged(query: String) {
                if (query.isFilled()) {
                    searchMovies(query)
                }
            }

            override fun onShowProgressBar(show: Boolean) {
                pb.setVisibleOrGone(show)
            }
        })
    }

    private fun searchMovies(query: String) {
        viewModel.onSearchMovies(query)
    }
}
