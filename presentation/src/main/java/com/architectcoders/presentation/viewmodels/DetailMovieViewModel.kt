package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.data.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailMovieViewModel(uiDispatcher: CoroutineDispatcher) :
    BaseViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        data class Loading(val movie: Movie) : UiModel()
    }

    fun onMovieDetailLoading(movie: Movie?) {
        launch {
            _model.value = UiModel.Loading(movie!!)
        }
    }
}
