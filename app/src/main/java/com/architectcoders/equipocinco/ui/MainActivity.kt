package com.architectcoders.equipocinco.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.equipocinco.R
import com.architectcoders.equipocinco.zzz.ApiRepo
import com.architectcoders.equipocinco.zzz.MainPresenter
import com.architectcoders.equipocinco.zzz.Movie
import com.architectcoders.generic.util.KLog
import com.architectcoders.generic.util.toast

class MainActivity
    : AppCompatActivity()
    , MainPresenter.View {

    private lateinit var activity: MainActivity

    private val presenter by lazy { MainPresenter(ApiRepo(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        activity = this@MainActivity
        presenter.onCreate(activity)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateData(movies: List<Movie>) {
        val s = movies.toString()
        toast(s)
        KLog.d(s)
    }
}
