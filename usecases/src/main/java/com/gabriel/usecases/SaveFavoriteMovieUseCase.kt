package com.gabriel.usecases

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

class SaveFavoriteMovieUseCase(private val moviesRepository: MoviesRepository) :
    MoviesBaseUseCase<Movie, Movie>() {

    override suspend fun useCaseExecution(params: Movie?): DataState<Movie> {
        return params?.let { moviesRepository.storeFavoriteMovie(params) }
            ?: DataState.Error("Error, expected a query argument value")
    }
}
