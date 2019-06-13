package com.sun_asterisk.moviedb_44.data.source

import com.sun_asterisk.moviedb_44.data.model.Actor
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.model.Producer
import com.sun_asterisk.moviedb_44.utils.Constant
import io.reactivex.Flowable
import io.reactivex.Observable

interface MovieDataSource {
    interface MovieLocalDataSource {
        fun isMovieFavorite(idMovie: Int): Flowable<Boolean>

        fun getAllMoviesFavorite() : Flowable<MutableList<Movie>>

        fun addMovieFavorite(movie: Movie)

        fun deleteMovieFavorite(movie: Movie)
    }

    interface MovieRemoteDataSource {
        fun getActors(movieId: Int): Observable<List<Actor>>
        fun getProducers(movieId: Int): Observable<List<Producer>>
        fun getMovieLatest(): Observable<Movie>
        fun getListMoviePopular(page: Int): Observable<MutableList<Movie>>
        fun getListMovieUpcoming(page: Int): Observable<MutableList<Movie>>
        fun getListMovieTopRated(page: Int): Observable<MutableList<Movie>>
        fun getListMovieNowPlaying(page: Int): Observable<MutableList<Movie>>
        fun searchMovie(search: String, page: Int = Constant.PAGE_DEFAULT,
            isAdult: Boolean = false): Observable<MutableList<Movie>>
    }
}
