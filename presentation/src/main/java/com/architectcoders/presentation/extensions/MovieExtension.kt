package com.architectcoders.presentation.extensions

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.architectcoders.domain.model.Movie
import com.architectcoders.equipocinco.MovieApplication
import com.architectcoders.generic.framework.extension.EMPTY
import java.math.RoundingMode
import java.text.DecimalFormat

private const val VOTE_RATING_PATTERN = "#.#"

fun Movie.getOriginalTitle(): String = if (title == originalTitle)
    EMPTY
else
    originalTitle

fun Movie.getPopularity(): String = "Popularity: $popularity"

fun Movie.getVoteAverage(): String = DecimalFormat(VOTE_RATING_PATTERN)
    .apply {
        roundingMode = RoundingMode.CEILING
    }.format(voteAverage / 2).toString()

fun Movie.getReleaseDateFormatted() = "Release date: $releaseDate"


@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

val Context.app: MovieApplication
    get() = applicationContext as MovieApplication