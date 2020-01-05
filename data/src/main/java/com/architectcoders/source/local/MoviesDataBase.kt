package com.architectcoders.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDb::class], version = 1)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesRoomDatabase? = null

        fun getDatabase(context: Context): MoviesRoomDatabase {
            return INSTANCE
                ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesRoomDatabase::class.java,
                    "movies_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}