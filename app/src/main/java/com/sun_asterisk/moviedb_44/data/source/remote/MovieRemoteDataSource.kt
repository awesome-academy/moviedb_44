package com.sun_asterisk.moviedb_44.data.source.remote

import com.sun_asterisk.moviedb_44.data.model.Actor
import com.sun_asterisk.moviedb_44.data.source.MovieDataSource
import com.sun_asterisk.moviedb_44.data.source.remote.config.service.MovieApi
import com.sun_asterisk.moviedb_44.data.source.remote.config.service.MovieFactory
import io.reactivex.Observable

class MovieRemoteDataSource private constructor(private val movieApi: MovieApi) :
    MovieDataSource.MovieRemoteDataSource {

    companion object {

        var instances = MovieRemoteDataSource(MovieFactory.create())

        fun getInstance(): MovieRemoteDataSource {
            return instances
        }
    }

    override fun getActors(movieId: Int): Observable<List<Actor>> {
        return movieApi.getListActors(movieId)
            .flatMap { actorResponse -> Observable.just(actorResponse.listActor) }
    }
}
