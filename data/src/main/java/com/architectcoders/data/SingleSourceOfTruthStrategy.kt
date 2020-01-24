package com.architectcoders.data

import com.architectcoders.data.Result.Status.*
import com.architectcoders.domain.state.DataState


suspend fun <T, A> singleSourceOfData(
    databaseQuery: suspend () -> T,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit,
    shouldFetch: () -> Boolean
): DataState<T> {
    if (shouldFetch()) {
        networkCall.invoke().also { resultNetworkCall ->
            when (resultNetworkCall.status) {
                SUCCESS -> {
                    resultNetworkCall.data?.let {
                        saveCallResult(it)
                    }
                }

                ERROR -> {
                    return DataState.Error(resultNetworkCall.message!!)
                }
            }
        }
    }

    return fetchFromDatabase(databaseQuery)
}

private suspend fun <T> fetchFromDatabase(databaseQuery: suspend () -> T) =
    databaseQuery.invoke().let { model ->
        DataState.Success(model)
    }


