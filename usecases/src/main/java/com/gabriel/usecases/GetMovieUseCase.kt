package com.gabriel.usecases

import com.architectcoders.domain.MoviesRepository
import com.architectcoders.domain.model.Movie
import com.architectcoders.domain.state.DataState

class GetMovieUseCase(private val moviesRepository: MoviesRepository) :
    MoviesBaseUseCase<Movie, Int>() {

    override suspend fun useCaseExecution(params: Int?): DataState<Movie> {
        return params?.let { moviesRepository.getMovieById(params) }
            ?: DataState.Error("Error, expected an id argument value")
    }
}
