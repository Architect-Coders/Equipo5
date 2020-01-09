package com.architectcoders.source.remote

import android.util.Log
import com.architectcoders.data.Result
import kotlinx.coroutines.Deferred

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Deferred<T>): Result<T> = try {
        val response = call().await()
        Result.success(response)
    } catch (e: Exception) {
        Log.d("Gabriel", "ERROR DOING THIS!! ${e.message} ")
        error(e.message ?: e.toString())
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }
}
