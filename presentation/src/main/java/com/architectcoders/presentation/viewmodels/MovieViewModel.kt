package com.architectcoders.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.model.Movie
import com.gabriel.usecases.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMovieListUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getSearchMovieListUseCase: GetSearchMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
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
        object RequestMovies : UiModel()
        data class Content(val movies: List<Movie>) : UiModel()
    }

    private fun refresh() {
        _model.value = UiModel.RequestMovies
    }

    fun onRequestPopularMovies() {
        launch {
            _model.value = UiModel.Loading
            getPopularMovieListUseCase.execute(::handleMoviesResponse, ::handleErrorResponse)
        }
    }

    fun onRequestTopRatedMovies() {
        launch {
            _model.value = UiModel.Loading
            getTopRatedMoviesUseCase.execute(::handleMoviesResponse, ::handleErrorResponse)
        }
    }

    fun onRequestFavoriteMovies() {
        launch {
            _model.value = UiModel.Loading
            getFavoriteMoviesUseCase.execute(::handleMoviesResponse, ::handleErrorResponse)
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
        Log.d("Throwable", "asasd")
        //TODO
    }
}
