package com.architectcoders.presentation.viewmodels

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.architectcoders.presentation.Scope
import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseViewModel(uiDispatcher: CoroutineDispatcher) : ViewModel(),
    Scope by Scope.Impl(uiDispatcher) {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
