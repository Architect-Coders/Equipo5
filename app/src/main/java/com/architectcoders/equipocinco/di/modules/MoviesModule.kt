package com.architectcoders.equipocinco.di.modules

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.gabriel.usecases.*
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
    fun getFavoriteMoviesUseCase(moviesRepository: MoviesRepository): GetFavoriteMoviesUseCase {
        return GetFavoriteMoviesUseCase(moviesRepository)
    }

    @Provides
    fun saveFavoriteMovieUseCase(moviesRepository: MoviesRepository): SaveFavoriteMovieUseCase {
        return SaveFavoriteMovieUseCase(moviesRepository)
    }

    @Provides
    fun movieViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
        getSearchMoviesUseCase: GetSearchMoviesUseCase,
        getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
        saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase
    ) = MovieViewModel(
        getPopularMoviesUseCase,
        getTopRatedMoviesUseCase,
        getSearchMoviesUseCase,
        getFavoriteMoviesUseCase,
        saveFavoriteMovieUseCase,
        Dispatchers.Main
    )
}

@Subcomponent(modules = [(MoviesModule::class)])
interface MoviesComponent {
    val movieViewModel: MovieViewModel
}