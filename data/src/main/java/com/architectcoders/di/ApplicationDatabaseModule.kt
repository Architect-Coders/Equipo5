package com.architectcoders.di

import android.content.Context
import com.architectcoders.data.SessionManager
import com.architectcoders.source.local.MovieDao
import com.architectcoders.source.local.MoviesRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class ApplicationDatabaseModule(private val context: Context) {

    @Provides
    fun getMoviesDao(): MovieDao {
        return MoviesRoomDatabase.getDatabase(context).moviesDao()
    }

    @Provides
    fun getSessionManager(): SessionManager {
        return SessionManager(context)
    }
}