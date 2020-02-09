package com.architectcoders.equipocinco.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.databinding.FragmentDetailMovieBinding
import com.architectcoders.equipocinco.ui.activity.BaseFragment
import com.architectcoders.presentation.di.modules.ViewModelProviderFactory
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import javax.inject.Inject


class DetailMovieFragment : BaseFragment() {

    companion object {
        const val MOVIE_ID_KEY = "DetailMovieFragment::id"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(DetailMovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailMovieBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail_movie, container, false
        )
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        arguments?.let { bundle ->
            bundle.getInt(MOVIE_ID_KEY).let {
                viewModel.onMovieDetailLoading(it)
            }
        }

        return binding.root
    }
}
