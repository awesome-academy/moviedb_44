package com.sun_asterisk.moviedb_44.data.repository

import com.sun_asterisk.moviedb_44.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_44.data.source.remote.MovieRemoteDataSource

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
}
