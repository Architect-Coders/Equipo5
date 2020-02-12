package com.architectcoders.equipocinco.di.modules

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import com.gabriel.usecases.GetMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.Dispatchers


@Module
class DetailMovieModule {

    @Provides
    fun getMovieUseCase(moviesRepository: MoviesRepository) = GetMovieUseCase(moviesRepository)

    @Provides
    fun detailMovieViewModel(getMovieUseCase: GetMovieUseCase) =
        DetailMovieViewModel(getMovieUseCase, Dispatchers.Main)

}

@Subcomponent(modules = [(DetailMovieModule::class)])
interface DetailMovieComponent {
    val detailViewModel: DetailMovieViewModel
}

