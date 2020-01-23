package com.architectcoders.presentation.di.modules

import androidx.lifecycle.ViewModel
import com.architectcoders.presentation.viewmodels.DetailMovieViewModel
import com.architectcoders.presentation.viewmodels.MovieViewModel
import com.gabriel.usecases.GetMovieUseCase
import com.gabriel.usecases.GetPopularMoviesUseCase
import com.gabriel.usecases.GetSearchMoviesUseCase
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class MoviesViewModelModule {

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)


    @Provides
    fun viewModelFactory(providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProviderFactory {
        return ViewModelProviderFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun movieViewModel(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getSearchMoviesUseCase: GetSearchMoviesUseCase
    ): ViewModel {
        return MovieViewModel(getPopularMoviesUseCase, getSearchMoviesUseCase, Dispatchers.Main)
    }

    @Provides
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    fun detailMovieViewModel(getMovieUseCase: GetMovieUseCase): ViewModel {
        return DetailMovieViewModel(getMovieUseCase, Dispatchers.Main)
    }
}