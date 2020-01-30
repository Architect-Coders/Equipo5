package com.architectcoders.equipocinco.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC LIMIT 20")
    fun getMovieList(): List<MovieDb>

    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun getMovieList(query: String): List<MovieDb>

    @Query("SELECT * FROM movies WHERE id LIKE :id")
    fun getMovie(id: Int): MovieDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDb>)
}

