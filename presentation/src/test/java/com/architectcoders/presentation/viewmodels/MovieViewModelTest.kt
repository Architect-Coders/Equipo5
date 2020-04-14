package com.architectcoders.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gabriel.usecases.GetFavoriteMoviesUseCase
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import com.gabriel.usecases.GetTopRatedMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Mock
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    lateinit var getSearchMovieListUseCase: GetSearchMoviesUseCase

    @Mock
    lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Mock
    lateinit var observer: Observer<MovieViewModel.UiModel>

    private lateinit var viewModel: MovieViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        viewModel = MovieViewModel(
            getPopularMoviesUseCase,
            getTopRatedMoviesUseCase,
            getSearchMovieListUseCase,
            getFavoriteMoviesUseCase,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `after Initialise it request movies`() = runBlocking {
        viewModel.model.observeForever(observer)
        verify(observer, times(1)).onChanged(MovieViewModel.UiModel.RequestMovies)
    }

    @Test
    fun onRequestPopularMovies() = runBlocking {
        viewModel.model.observeForever(observer)

        `when`(viewModel.onRequestPopularMovies()).thenCallRealMethod()
        verify(observer, times(1)).onChanged(MovieViewModel.UiModel.Loading)
        verify(getPopularMoviesUseCase, times(0)).execute({
            viewModel.handleMoviesResponse(it)
        },{
            viewModel.handleErrorResponse(it)
        })
    }

    @Test
    fun onRequestTopRatedMovies() = runBlocking {
        viewModel.model.observeForever(observer)

        `when`(viewModel.onRequestTopRatedMovies()).thenCallRealMethod()
        verify(observer, times(1)).onChanged(MovieViewModel.UiModel.Loading)
        verify(getTopRatedMoviesUseCase, times(0)).execute({
            viewModel.handleMoviesResponse(it)
        },{
            viewModel.handleErrorResponse(it)
        })
    }

    @Test
    fun onRequestFavoriteMovies() = runBlocking {
        viewModel.model.observeForever(observer)

        `when`(viewModel.onRequestFavoriteMovies()).thenCallRealMethod()
        verify(observer, times(1)).onChanged(MovieViewModel.UiModel.Loading)
        verify(getPopularMoviesUseCase, times(0)).execute({
            viewModel.handleMoviesResponse(it)
        },{
            viewModel.handleErrorResponse(it)
        })
    }

    @Test
    fun onSearchMovies() = runBlocking {
        viewModel.model.observeForever(observer)

        val query = "001"
        `when`(viewModel.onSearchMovies(query)).thenCallRealMethod()
        verify(observer, times(1)).onChanged(MovieViewModel.UiModel.Loading)
        verify(getSearchMovieListUseCase, times(0)).execute({
            viewModel.handleMoviesResponse(it)
        },{
            viewModel.handleErrorResponse(it)
        },
            query
        )
    }

}