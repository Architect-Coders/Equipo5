package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.model.Movie
import com.gabriel.usecases.GetMovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val getMovieUseCase: GetMovieUseCase,uiDispatcher: CoroutineDispatcher) :
    BaseViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val movie: Movie) : UiModel()
    }

    fun onMovieDetailLoading(id: Int) {
        launch {
            _model.value = UiModel.Loading
            getMovieUseCase.execute(::handleSuccessMovie, params = id)
        }
    }

    private fun handleSuccessMovie(movie: Movie) {
        _model.value = UiModel.Content(movie)    }

}
