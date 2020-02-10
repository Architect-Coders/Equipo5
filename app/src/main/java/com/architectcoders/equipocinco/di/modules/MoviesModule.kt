package com.architectcoders.equipocinco.di.modules

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import com.gabriel.usecases.GetTopRatedMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.Dispatchers


@Module
class MoviesModule {

    @Provides
    fun getSearchMoviesUseCase(moviesRepository: MoviesRepository) =
        GetSearchMoviesUseCase(moviesRepository)


    @Provides
    fun getPopularMoviesUseCase(moviesRepository: MoviesRepository) =
        GetPopularMoviesUseCase(moviesRepository)

    @Provides
    fun getTopRatedMoviesUseCase(moviesRepository: MoviesRepository) =
        GetTopRatedMoviesUseCase(moviesRepository)


    @Provides
    fun movieViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
        getSearchMoviesUseCase: GetSearchMoviesUseCase
    ) = MovieViewModel(
        getPopularMoviesUseCase,
        getTopRatedMoviesUseCase,
        getSearchMoviesUseCase,
        Dispatchers.Main
    )
}

@Subcomponent(modules = [(MoviesModule::class)])
interface MoviesComponent {
    val movieViewModel: MovieViewModel
}