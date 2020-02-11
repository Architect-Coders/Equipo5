package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.model.Movie
import com.architectcoders.presentation.common.Event
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import com.gabriel.usecases.GetTopRatedMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMovieListUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
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

    private val _modelNavigation = MutableLiveData<Event<NavigationModel>>()
    val modelNavigation: LiveData<Event<NavigationModel>>
        get() = _modelNavigation


    sealed class UiModel {
        object Loading : UiModel()
        object RequestMovies : UiModel()
        data class Content(val movies: List<Movie>) : UiModel()
    }

    class NavigationModel(val movie: Movie)

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

    fun onSearchMovies(query: String) {
        launch {
            _model.value = UiModel.Loading
            getSearchMovieListUseCase.execute(::handleMoviesResponse, ::handleErrorResponse, query)
        }
    }

    fun onSelectedMovie(movie: Movie) {
        _modelNavigation.value = Event(
                NavigationModel(movie)
            )
    }

    private fun handleMoviesResponse(movies: List<Movie>) {
        _model.value = UiModel.Content(movies)
    }

    private fun handleErrorResponse(throwable: Throwable) {
        //TODO
    }
}
