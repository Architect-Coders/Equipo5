package com.architectcoders.source.local

import com.architectcoders.Movie
import com.architectcoders.mappers.toDomainMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RoomDataSource(private val movieDao: MovieDao) : LocalDataSource {

    override suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        movieDao.getMovieList().map { movieDb ->
            movieDb.toDomainMovie()
        }
    }

    override suspend fun getPopularMovies(query: String): List<Movie> {
        return movieDao.getMovieList(query).map { movieDb ->
            movieDb.toDomainMovie()
        }
    }

    override suspend fun saveMovies(movies: List<MovieDb>) {
        withContext(Dispatchers.IO) { movieDao.insertAll(movies) }
    }
}