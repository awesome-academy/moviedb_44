package com.sun_asterisk.moviedb_44.data.source

import com.sun_asterisk.moviedb_44.data.model.Actor
import com.sun_asterisk.moviedb_44.data.model.Producer
import io.reactivex.Observable

interface MovieDataSource {
    interface MovieLocalDataSource

    interface MovieRemoteDataSource {
        fun getActors(movieId: Int): Observable<List<Actor>>
        fun getProducers(movieId: Int): Observable<List<Producer>>
    }
}
