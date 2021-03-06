package com.architectcoders.equipocinco.data.mappers

import com.architectcoders.domain.model.Movie
import com.architectcoders.equipocinco.data.source.local.MovieDb as RoomMovie
import com.architectcoders.equipocinco.data.source.remote.MovieDto as ServerMovie

fun RoomMovie.toDomainMovie(): Movie =
    Movie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favorite
    )

val mapDomainMovieToDb: (Movie) -> RoomMovie = { movie ->
    RoomMovie(
        movie.id,
        movie.title,
        movie.overview,
        movie.releaseDate,
        movie.posterPath,
        movie.backdropPath,
        movie.originalLanguage,
        movie.originalTitle,
        movie.popularity,
        movie.voteAverage,
        movie.favorite
    )
}

val mapServerMovieToDomain: (ServerMovie) -> Movie = { movie ->
    Movie(
        movie.id,
        movie.title,
        movie.overview,
        movie.releaseDate ?: "no release date ",
        movie.posterPath ?: "no poster path",
        movie.backdropPath ?: "no poster backdropPath",
        movie.originalLanguage,
        movie.originalTitle,
        movie.popularity,
        movie.voteAverage,
        false
    )
}


