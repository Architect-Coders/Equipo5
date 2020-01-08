package com.architectcoders.source.local

import com.architectcoders.Movie


interface LocalDataSource {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getPopularMovies(query: String): List<Movie>
    suspend fun saveMovies(movies: List<MovieDb>)
}