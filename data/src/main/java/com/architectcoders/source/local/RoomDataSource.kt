package com.architectcoders.source.local

import com.architectcoders.Movie
import com.architectcoders.generic.framework.extension.enclosingPercentage
import com.architectcoders.mappers.mapDomainMovieToDb
import com.architectcoders.mappers.toDomainMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RoomDataSource(private val movieDao: MovieDao) : LocalDataSource {

    override suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        movieDao.getMovieList().map { movieDb ->
            movieDb.toDomainMovie()
        }
    }

    override suspend fun getPopularMoviesByLocation(locationCode: String?): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.getMovieList().map { movieDb ->
            movieDb.toDomainMovie()
        }
    }

    override suspend fun getPopularMovies(query: String): List<Movie> = withContext(Dispatchers.IO) {
            movieDao.getMovieList(query.enclosingPercentage()).map { movieDb -> movieDb.toDomainMovie() }
        }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.insertAll(movies.map { movie -> mapDomainMovieToDb(movie) })
    }

}
