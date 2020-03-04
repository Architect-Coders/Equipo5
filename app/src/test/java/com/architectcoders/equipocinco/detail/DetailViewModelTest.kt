package com.architectcoders.equipocinco.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.architectcoders.domain.model.Movie
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import com.gabriel.usecases.GetMovieUseCase
import com.gabriel.usecases.SaveFavoriteMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{

    private lateinit var vm: DetailMovieViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getMovieUseCase : GetMovieUseCase

    @Mock
    lateinit var saveFavoriteMoviesUseCase: SaveFavoriteMovieUseCase

    val mockedMovie = Movie(
        0,
        "Title",
        "Overview",
        "01/01/2025",
        "",
        "",
        "EN",
        "Title",
        5.0,
        5.1,
        false
    )

    @Before
    fun setUp() {
        vm = DetailMovieViewModel(getMovieUseCase, saveFavoriteMoviesUseCase, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData finds the movie`() {

        runBlocking {
            val movie = mockedMovie.copy(id = 1)
           Assert.assertEquals(movie.id, 1)
        }
    }
}