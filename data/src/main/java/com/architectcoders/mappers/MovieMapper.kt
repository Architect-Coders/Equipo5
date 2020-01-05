package com.architectcoders.mappers

import com.architectcoders.Movie
import com.architectcoders.source.local.MovieDb
import com.architectcoders.source.remote.MovieDto


val mapRemoteMovieToDb: (MovieDto) -> MovieDb = { movie ->
    MovieDb(
        movie.id,
        movie.adult,
        movie.backdropPath,
        //  movie.genreIds,
        movie.originalLanguage,
        movie.originalTitle,
        movie.overview,
        movie.popularity,
        movie.posterPath,
        movie.releaseDate,
        movie.originalTitle,
        movie.video,
        movie.voteAverage,
        movie.voteCount
    )
}


fun MovieDb.toDomainMovie(): Movie =
    Movie(
        id,
        adult,
        backdropPath,
        //  movie.genreIds,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        originalTitle,
        video,
        voteAverage,
        voteCount
    )

