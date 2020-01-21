package com.architectcoders.source.local

interface LocationDataSource {
    suspend fun getLastLocation(): String?
}