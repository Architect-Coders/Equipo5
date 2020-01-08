package com.architectcoders.data

import com.architectcoders.data.Result.Status.*


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
                    saveCallResult(resultNetworkCall.data!!)
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


/***
 * This class will be moved to the domain layer later on
 * since any domain DataState should be out of the data(repository) scope
 */
sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val message: String, val cause: Exception? = null) : DataState<Nothing>()
}

