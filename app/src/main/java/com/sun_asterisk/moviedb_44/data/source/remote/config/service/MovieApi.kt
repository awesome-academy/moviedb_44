package com.sun_asterisk.moviedb_44.data.source.remote.config.service

import com.sun_asterisk.moviedb_44.BuildConfig
import com.sun_asterisk.moviedb_44.data.source.remote.config.response.ActorResponse
import com.sun_asterisk.moviedb_44.data.source.remote.config.response.ProducerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/{movie_id}/credits?api_key=" + BuildConfig.API_KEY)
    fun getListActors(@Path("movie_id") movieId: Int): Observable<ActorResponse>

    @GET("movie/{movie_id}/credits?api_key=" + BuildConfig.API_KEY)
    fun getListProducers(@Path("movie_id") movieId: Int): Observable<ProducerResponse>
}
