package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.*
import com.architectcoders.data.ApiRepo
import com.architectcoders.data.DataState
import com.architectcoders.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieViewModel(private val apiRepo: ApiRepo, uiDispatcher: CoroutineDispatcher) :
    BaseViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val movies: List<Movie>) : UiModel()
        object RequestMovies : UiModel()
    }

    private fun refresh() {
        _model.value = UiModel.RequestMovies
    }

    fun onRequestMovieList() {
        launch {
            _model.value = UiModel.Loading
            handleMoviesResponse(apiRepo.getPopularMovies())
        }
    }

    fun onSearchMovies(query: String) {
        launch {
            _model.value = UiModel.Loading
            handleMoviesResponse(apiRepo.searchMovies(query))
        }
    }

    private fun handleMoviesResponse(dataState: DataState<List<Movie>>) {
        when (dataState) {
            is DataState.Success -> { _model.value = UiModel.Content(dataState.data) }
            is DataState.Error -> {}
        }
    }
}