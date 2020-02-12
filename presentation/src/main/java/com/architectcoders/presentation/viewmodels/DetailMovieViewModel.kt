package com.architectcoders.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.model.Movie
import com.architectcoders.presentation.extensions.getOriginalTitle
import com.architectcoders.presentation.extensions.getPopularity
import com.architectcoders.presentation.extensions.getReleaseDateFormatted
import com.architectcoders.presentation.extensions.getVoteAverage
import com.gabriel.usecases.GetMovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    uiDispatcher: CoroutineDispatcher
) : BaseViewModel(uiDispatcher) {

    companion object {
        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/original/"
    }

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _originalTitle = MutableLiveData<String>()
    val originalTitle: LiveData<String> = _originalTitle

    private val _popularity = MutableLiveData<String>()
    val popularity: LiveData<String> = _popularity

    private val _rate = MutableLiveData<String>()
    val rate: LiveData<String> = _rate

    private val _released = MutableLiveData<String>()
    val released: LiveData<String> = _released

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    fun onMovieDetailLoading(id: Int) {
        launch {
            getMovieUseCase.execute(::handleSuccessMovie, params = id)
        }
    }

    private fun handleSuccessMovie(movie: Movie) {
        movie.run {
            _url.value = "$POSTER_BASE_URL${posterPath}"
            _title.value = title
            _originalTitle.value = getOriginalTitle()
            _popularity.value = getPopularity()
            _rate.value = getVoteAverage()
            _released.value = getReleaseDateFormatted()
            _description.value = overview
        }

    }
}
