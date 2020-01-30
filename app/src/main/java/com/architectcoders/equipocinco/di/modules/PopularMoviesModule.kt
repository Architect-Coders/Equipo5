package com.architectcoders.equipocinco.di.modules

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.Dispatchers


@Module
class PopularMoviesModule {

    @Provides
    fun getSearchMoviesUseCase(moviesRepository: MoviesRepository) = GetSearchMoviesUseCase(moviesRepository)


    @Provides
    fun getPopularMoviesUseCase(moviesRepository: MoviesRepository) = GetPopularMoviesUseCase(moviesRepository)


    @Provides
    fun movieViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getSearchMoviesUseCase: GetSearchMoviesUseCase
    ) = MovieViewModel(getPopularMoviesUseCase, getSearchMoviesUseCase, Dispatchers.Main)
}

@Subcomponent(modules = [(PopularMoviesModule::class)])
interface PopularMoviesComponent {
    val movieViewModel: MovieViewModel
}