package com.architectcoders.presentation.di.modules

import androidx.lifecycle.ViewModel
import com.architectcoders.presentation.viewmodels.MovieViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * Created by Gabriel Pozo Guzman on 2019-12-13.
 */

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
    fun movieViewModel(): ViewModel {
        return MovieViewModel()
    }
}