package com.gabriel.usecases

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) :
    MoviesBaseUseCase<List<Movie>, Any>() {

    public override suspend fun useCaseExecution(params: Any?): DataState<List<Movie>> {
        return moviesRepository.getPopularMovies()
    }
}
