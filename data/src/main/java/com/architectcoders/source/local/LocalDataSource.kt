package com.architectcoders.source.local

import com.architectcoders.domain.model.Movie


interface LocalDataSource {
    suspend fun getPopularMovies(locationCode : String?): List<Movie>
    suspend fun getPopularMoviesBySearch(query: String): List<Movie>
    suspend fun getMovie(id: Int): Movie
    suspend fun saveMovies(movies: List<Movie>)
}