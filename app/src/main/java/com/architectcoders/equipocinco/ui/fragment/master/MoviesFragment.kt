package com.architectcoders.equipocinco.ui.fragment.master

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.architectcoders.domain.model.Movie
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.common.PermissionRequester
import com.architectcoders.equipocinco.di.modules.MoviesComponent
import com.architectcoders.equipocinco.di.modules.MoviesModule
import com.architectcoders.equipocinco.extensions.app
import com.architectcoders.equipocinco.extensions.getViewModel
import com.architectcoders.equipocinco.extensions.newInstance
import com.architectcoders.equipocinco.framework.SearchManager
import com.architectcoders.equipocinco.ui.adapter.MovieAdapter
import com.architectcoders.equipocinco.ui.fragment.BaseFragment
import com.architectcoders.equipocinco.ui.fragment.detail.DetailMovieFragment
import com.architectcoders.generic.framework.extension.isFilled
import com.architectcoders.generic.framework.extension.view.setVisibleOrGone
import com.architectcoders.presentation.common.Event
import com.architectcoders.presentation.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.progress_bar.*
import kotlinx.android.synthetic.main.search.*

abstract class MoviesFragment : BaseFragment() {

    private lateinit var coarsePermissionRequester: PermissionRequester
    private lateinit var component: MoviesComponent
    private var adapter: MovieAdapter? = null

    protected val viewModel: MovieViewModel by lazy { getViewModel { component.movieViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.run {
            component = app.applicationComponent.plus(MoviesModule())
        } ?: throw Exception("Invalid Activity")


        coarsePermissionRequester =
            PermissionRequester(activity, Manifest.permission.ACCESS_COARSE_LOCATION)

        coarsePermissionRequester.request {
            viewModel.model.observe(viewLifecycleOwner, Observer(::updateUI))
        }

        navigationObserver()
        initClSearch()
    }


    private fun navigationObserver() {
        viewModel.modelNavigation.observe(viewLifecycleOwner, Observer(::navigationResult))
    }

    private fun updateUI(model: MovieViewModel.UiModel) {
        when (model) {
            is MovieViewModel.UiModel.Loading -> pb.show()
            is MovieViewModel.UiModel.RequestMovies -> onRequestMovies()
            is MovieViewModel.UiModel.Content -> updateData(model.movies)
        }
    }

    private fun updateData(movies: List<Movie>) {
        initAdapter(movies)
    }

    private fun navigationResult(navigationModel: Event<MovieViewModel.NavigationModel>) {
        navigationModel.getContentIfNotHandled()?.let { navModel ->
            mFragmentNavigation.pushFragment(DetailMovieFragment.newInstance(navModel.movie.id))
        }
    }

    private fun initAdapter(items: List<Movie>) {
        rv?.let {
            rv.layoutManager = GridLayoutManager(activity, 3)
            adapter = MovieAdapter(items.toMutableList(), viewModel::onSelectedMovie)
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

    abstract fun onRequestMovies()

}
