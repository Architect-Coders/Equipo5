package com.architectcoders.equipocinco.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC LIMIT 20")
    fun getPopularMovies(): List<MovieDb>

    @Query("SELECT * FROM movies ORDER BY voteAverage DESC LIMIT 20")
    fun getTopRatedMovies(): List<MovieDb>

    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun searchMovies(query: String): List<MovieDb>

    @Query("SELECT * FROM movies WHERE id LIKE :id")
    fun getMovie(id: Int): MovieDb

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<MovieDb>)

    @Query("SELECT * FROM movies WHERE favorite = 1")
    fun getFavoriteMovies(): Flowable<List<MovieDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: MovieDb)
}
