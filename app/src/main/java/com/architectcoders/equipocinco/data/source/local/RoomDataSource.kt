package com.architectcoders.equipocinco.data.source.local

import com.architectcoders.domain.model.Movie
import com.architectcoders.generic.framework.extension.enclosingPercentage
import com.architectcoders.equipocinco.data.mappers.mapDomainMovieToDb
import com.architectcoders.equipocinco.data.mappers.toDomainMovie
import com.architectcoders.source.local.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RoomDataSource(private val movieDao: MovieDao) :
    LocalDataSource {

    override suspend fun getPopularMovies(locationCode: String?): List<Movie> =
        withContext(Dispatchers.IO) {
            movieDao.getPopularMovies().map { movieDb ->
                movieDb.toDomainMovie()
            }
        }

    override suspend fun getTopRatedMovies(locationCode: String?): List<Movie> =
        withContext(Dispatchers.IO) {
            movieDao.getTopRatedMovies().map { movieDb ->
                movieDb.toDomainMovie()
            }
        }

    override suspend fun searchMovies(query: String): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.searchMovies(query.enclosingPercentage())
            .map { movieDb -> movieDb.toDomainMovie() }
    }

    override suspend fun getMovie(id: Int): Movie = withContext(Dispatchers.IO) {
        movieDao.getMovie(id).toDomainMovie()
    }

    override suspend fun getFavoriteMovies(): List<Movie> =
        withContext(Dispatchers.IO) {
            movieDao.getFavoriteMovies()
                .map { movieDb -> movieDb.toDomainMovie() }
        }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.insertAll(movies.map { movie ->
            mapDomainMovieToDb(
                movie
            )
        })
    }

    override suspend fun saveFavoriteMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insertFavoriteMovie(mapDomainMovieToDb(movie))
        movie
    }
}
