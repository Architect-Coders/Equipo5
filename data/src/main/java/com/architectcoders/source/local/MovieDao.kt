package com.architectcoders.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovieList(): List<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDb>)
}

