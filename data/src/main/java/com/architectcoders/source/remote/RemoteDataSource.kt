package com.architectcoders.source.remote

import com.architectcoders.data.Result
import com.architectcoders.domain.model.Movie


interface RemoteDataSource {
    suspend fun fetchMovies(locationCode : String): Result<List<Movie>>
    suspend fun fetchMoviesBySearch(query: String): Result<List<Movie>>
    suspend fun getTopRatedMovies(locationCode : String): Result<List<Movie>>

}