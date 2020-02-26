package com.architectcoders.domain

import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

interface MoviesRepository {
    suspend fun getPopularMovies(): DataState<List<Movie>>
    suspend fun getTopRatedMovies(): DataState<List<Movie>>
    suspend fun searchMovies(query: String): DataState<List<Movie>>
    suspend fun getMovieById(id: Int):  DataState<Movie>
    suspend fun getFavoriteMovies() : DataState<List<Movie>>
    suspend fun storeFavoriteMovie(movie: Movie) : DataState<Movie>
}
