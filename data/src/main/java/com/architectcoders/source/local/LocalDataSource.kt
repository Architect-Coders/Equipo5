package com.architectcoders.source.local

import com.architectcoders.domain.model.Movie

interface LocalDataSource {
    suspend fun getPopularMovies(locationCode: String?): List<Movie>
    suspend fun getTopRatedMovies(locationCode: String?): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovie(id: Int): Movie
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun getFavoriteMovies(): List<Movie>
}
