package com.sun_asterisk.moviedb_44.data.repository

import com.sun_asterisk.moviedb_44.data.model.Actor
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_44.data.source.remote.MovieRemoteDataSource
import io.reactivex.Observable

class MovieRepository private constructor(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) {
    companion object {
        private var sInstance: MovieRepository? = null

        @JvmStatic
        fun getInstance(local: MovieLocalDataSource, remote: MovieRemoteDataSource): MovieRepository {
            if (sInstance == null) {
                synchronized(MovieRepository::class.java) {
                    sInstance = MovieRepository(local, remote)
                }
            }
            return sInstance!!
        }
    }

    fun getActors(movieId: Int): Observable<List<Actor>> = remote.getActors(movieId)

    fun getMovieLatest(): Observable<Movie> = remote.getMovieLatest()

    fun getListMoviePopular(page: Int): Observable<MutableList<Movie>> = remote.getListMoviePopular(page)

    fun getListMovieUpcoming(page: Int): Observable<MutableList<Movie>> = remote.getListMovieUpcoming(page)

    fun getListMovieTopRated(page: Int): Observable<MutableList<Movie>> = remote.getListMovieTopRated(page)

    fun getListMovieNowPlaying(page: Int): Observable<MutableList<Movie>> = remote.getListMovieNowPlaying(page)
}
