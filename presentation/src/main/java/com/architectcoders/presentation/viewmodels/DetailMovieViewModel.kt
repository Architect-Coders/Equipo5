package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.Movie
import com.architectcoders.data.ApiRepo
import com.architectcoders.data.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val apiRepo: ApiRepo, uiDispatcher: CoroutineDispatcher) :
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
            _model.value = UiModel.Content(apiRepo.getMovieById(id))
        }
    }

}
