package com.architectcoders.data

import com.architectcoders.data.FlagSourceData.*
import com.architectcoders.data.Result.Status.*

enum class FlagSourceData {
    FETCH_FROM_NETWORK,
    NO_NETWORK_CONNECTION,
    TIMEOUT_SOURCE_DATA
}

suspend fun <T, A> singleSourceOfData(
    databaseQuery: suspend () -> T,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit,
    shouldFetch: () -> FlagSourceData
): DataState<T> {
    val flag = shouldFetch()
    if (flag == FETCH_FROM_NETWORK) {
        networkCall.invoke().also { resultNetworkCall ->
            return when (resultNetworkCall.status) {
                SUCCESS -> {
                    saveCallResult(resultNetworkCall.data!!)
                    fetchFromDatabase(databaseQuery, flag)
                }

                ERROR -> {
                    DataState.Error(resultNetworkCall.message!!)
                }
            }
        }
    }

    return fetchFromDatabase(databaseQuery, flag)
}

private suspend fun <T> fetchFromDatabase(databaseQuery: suspend () -> T, flag: FlagSourceData) =
    databaseQuery.invoke().let { model ->
        DataState.Success(model, flag)
    }


/***
 * This class will be moved to the domain layer later on
 * since DataState should be out of the data(repository) scope
 */
sealed class DataState<out T> {
    data class Success<T>(val data: T, val flag: FlagSourceData) : DataState<T>()
    data class Error(val message: String, val cause: Exception? = null) : DataState<Nothing>()
}

