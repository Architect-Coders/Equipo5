package com.architectcoders.source.local

import com.architectcoders.domain.model.Movie
import com.architectcoders.generic.framework.extension.enclosingPercentage
import com.architectcoders.mappers.mapDomainMovieToDb
import com.architectcoders.mappers.toDomainMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RoomDataSource(private val movieDao: MovieDao) : LocalDataSource {

    override suspend fun getPopularMovies(locationCode: String?): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.getMovieList().map { movieDb ->
            movieDb.toDomainMovie()
        }
    }

    override suspend fun getPopularMoviesBySearch(query: String): List<Movie> = withContext(Dispatchers.IO) {
            movieDao.getMovieList(query.enclosingPercentage()).map { movieDb -> movieDb.toDomainMovie() }
        }

    override suspend fun getMovie(id: Int): Movie = withContext(Dispatchers.IO) {
        movieDao.getMovie(id).toDomainMovie()
    }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.insertAll(movies.map { movie -> mapDomainMovieToDb(movie) })
    }

}
