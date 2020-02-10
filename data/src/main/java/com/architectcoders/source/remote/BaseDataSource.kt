package com.architectcoders.source.remote

import com.architectcoders.data.Result
import kotlinx.coroutines.Deferred

abstract class BaseDataSource {

    protected suspend fun <T, D> getResult(mapper: (T) -> D, call: suspend () -> Deferred<T>): Result<D> = try {
        val response = call().await()
        Result.success(mapper(response))
    } catch (e: Exception) {
        error(e.message ?: e.toString())
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }
}
