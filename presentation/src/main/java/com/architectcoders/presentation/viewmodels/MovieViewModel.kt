package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.*
import com.architectcoders.domain.model.Movie
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMovieListUseCase: GetPopularMoviesUseCase,
    private val getSearchMovieListUseCase: GetSearchMoviesUseCase,
    uiDispatcher: CoroutineDispatcher
) :
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
            getPopularMovieListUseCase.execute(::handleMoviesResponse, ::handleErrorResponse)
        }
    }

    fun onSearchMovies(query: String) {
        launch {
            _model.value = UiModel.Loading
            getSearchMovieListUseCase.execute(::handleMoviesResponse, ::handleErrorResponse, query)
        }
    }

    private fun handleMoviesResponse(movies: List<Movie>) {
        _model.value = UiModel.Content(movies)
    }

    private fun handleErrorResponse(throwable: Throwable) {
        //TODO
    }
}