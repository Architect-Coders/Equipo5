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

    private var movieId: Int? = null

    sealed class UiModel {
        data class Loading(val movie: Movie) : UiModel()
    }

    fun onMovieDetailLoading(movieId: Int) {
        this.movieId = movieId
        launch {
            _model.value = UiModel.Loading(Movie())
        }
    }
}
