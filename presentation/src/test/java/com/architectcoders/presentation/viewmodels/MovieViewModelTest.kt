package com.architectcoders.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gabriel.usecases.GetFavoriteMoviesUseCase
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import com.gabriel.usecases.GetTopRatedMoviesUseCase
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Mock
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    lateinit var getSearchMoviesUseCase: GetSearchMoviesUseCase

    @Mock
    lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Mock
    lateinit var observer: Observer<MovieViewModel.UiModel>

    private lateinit var vm: MovieViewModel

    @Before
    fun setUp() {
        vm = MovieViewModel(
            getPopularMoviesUseCase,
            getTopRatedMoviesUseCase,
            getSearchMoviesUseCase,
            getFavoriteMoviesUseCase,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `Observing LiveData launches request movies`() {
        vm.model.observeForever(observer)

        verify(observer).onChanged(MovieViewModel.UiModel.RequestMovies)
    }

//    @Test
//    fun `After requesting the permission, loading is shown`() {
//        runBlocking {
//            val movies = listOf(mockedMovie.copy(id = 1))
//            whenever(getPopularMoviesUseCase.useCaseExecution(null)).thenReturn(
//                DataState.Success(
//                    movies
//                )
//            )
//
//            vm.onRequestPopularMovies()
//
//            verify(observer).onChanged(MovieViewModel.UiModel.Content(movies))
//        }
//    }
}
