package com.sun_asterisk.moviedb_44.data.source.local

import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.source.MovieDataSource
import com.sun_asterisk.moviedb_44.data.source.local.config.dao.MovieDAO
import io.reactivex.Flowable

class MovieLocalDataSource private constructor(private val movieDAO: MovieDAO) :
    MovieDataSource.MovieLocalDataSource {
    override fun isMovieFavorite(idMovie: Int): Flowable<Boolean> = movieDAO.isMovieFavorite(idMovie)

    override fun getAllMoviesFavorite(): Flowable<MutableList<Movie>> = movieDAO.getAllMoviesFavorite()

    override fun addMovieFavorite(movie: Movie) = movieDAO.addMovieFavorite(movie)

    override fun deleteMovieFavorite(movie: Movie) = movieDAO.deleteMovieFavorite(movie)

    companion object {
        private var sInstance: MovieLocalDataSource? = null

        @JvmStatic
        fun getInstance(movieDAO: MovieDAO): MovieLocalDataSource {
            synchronized(MovieLocalDataSource::class.java) {
                if (sInstance == null) {
                    sInstance = MovieLocalDataSource(movieDAO)
                }
            }
            return sInstance!!
        }
    }
}
