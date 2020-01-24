package com.architectcoders.equipocinco.extensions

import com.architectcoders.domain.model.Movie
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