package com.architectcoders.equipocinco.data.source.remote

import com.architectcoders.data.Result
import com.architectcoders.domain.model.Movie
import com.architectcoders.equipocinco.data.mappers.mapServerMovieToDomain
import com.architectcoders.source.remote.BaseDataSource
import com.architectcoders.source.remote.RemoteDataSource

class MovieListRemoteDataSource constructor(private val service: ApiService) : BaseDataSource(),
    RemoteDataSource {

    override suspend fun fetchMovies(locationCode: String): Result<List<Movie>> =
        getResult(::mapResultToDomainModel) { service.getPopularMoviesAsync(region = locationCode) }


    override suspend fun fetchMoviesBySearch(query: String) =
        getResult(::mapResultToDomainModel) { service.searchMoviesAsync(query) }


    override suspend fun getTopRatedMovies(locationCode: String): Result<List<Movie>> =
        getResult(::mapResultToDomainModel) { service.getTopRatedMoviesAsync(region = locationCode) }


    private fun mapResultToDomainModel(movieDbResult: MovieDbResult) =
        movieDbResult.results.map(mapServerMovieToDomain)

}

