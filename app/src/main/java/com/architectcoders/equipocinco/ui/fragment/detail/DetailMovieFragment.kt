package com.architectcoders.equipocinco.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.databinding.FragmentDetailMovieBinding
import com.architectcoders.equipocinco.di.modules.DetailMovieComponent
import com.architectcoders.equipocinco.di.modules.DetailMovieModule
import com.architectcoders.equipocinco.extensions.app
import com.architectcoders.equipocinco.extensions.getViewModel
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel

class DetailMovieFragment : Fragment() {

    companion object {
        const val MOVIE_ID_KEY = "DetailMovieFragment::id"
    }

    private lateinit var component: DetailMovieComponent
    private val viewModel: DetailMovieViewModel by lazy { getViewModel { component.detailViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            component = app.applicationComponent.plus(DetailMovieModule())
        } ?: throw Exception("Invalid Activity")
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
