package com.gabriel.usecases

import com.architectcoders.domain.state.DataState

abstract class MoviesBaseUseCase<T, Params> {

    suspend fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit = {}, params: Params? = null) {
        when (val dataState = useCaseExecution(params)) {
            is DataState.Success -> onSuccess.invoke(dataState.data)
            is DataState.Error -> onError.invoke(Throwable(dataState.message))
        }
    }

    protected abstract suspend fun useCaseExecution(params: Params?): DataState<T>
}
