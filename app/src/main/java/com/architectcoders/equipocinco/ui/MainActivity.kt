package com.architectcoders.equipocinco.ui


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.data.Movie
import com.architectcoders.equipocinco.R
import com.architectcoders.generic.util.KLog
import com.architectcoders.generic.util.toast
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.architectcoders.presentation.viewmodels.MovieViewModel.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
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
        val s = movies.toString()
        toast(s)
        KLog.d(s)
    }
}
