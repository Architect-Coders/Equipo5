package com.gabriel.usecases

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

class GetFavoriteMoviesUseCase(private val moviesRepository: MoviesRepository) :
    MoviesBaseUseCase<List<Movie>, Any>() {

    override suspend fun useCaseExecution(params: Any?): DataState<List<Movie>> {
        return params?.let { moviesRepository.getFavoriteMovies() }
            ?: DataState.Error("Stored movies not available")
    }
}
