package com.architectcoders.equipocinco.zzz

import com.architectcoders.data.Movie
import kotlinx.coroutines.launch

class MainPresenter(private val repo: ApiRepo) : Scope by Scope.Impl() {

    interface View {
        fun updateData(movies: List<Movie>)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

        launch {
            view.updateData(repo.getPopularMovies().results)
        }

        // Test commit
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}
