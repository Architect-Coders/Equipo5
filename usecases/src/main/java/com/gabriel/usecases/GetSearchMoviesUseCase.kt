package com.gabriel.usecases

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

class GetSearchMoviesUseCase(private val moviesRepository: MoviesRepository) :
    MoviesBaseUseCase<List<Movie>, String>() {

    override suspend fun useCaseExecution(params: String?): DataState<List<Movie>> {
        return params?.let { moviesRepository.searchMovies(params) }
            ?: DataState.Error("Error, expected a query argument value")
    }
}
