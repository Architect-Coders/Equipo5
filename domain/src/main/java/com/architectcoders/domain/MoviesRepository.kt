package com.architectcoders.domain

import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

interface MoviesRepository {
    suspend fun getPopularMovies(): DataState<List<Movie>>
    suspend fun searchMovies(query: String): DataState<List<Movie>>
    suspend fun getMovieById(id: Int):  DataState<Movie>
}