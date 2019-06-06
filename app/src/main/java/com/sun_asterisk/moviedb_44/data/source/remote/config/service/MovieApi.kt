package com.sun_asterisk.moviedb_44.data.source.remote.config.service

import com.sun_asterisk.moviedb_44.BuildConfig
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.source.remote.config.response.ActorResponse
import com.sun_asterisk.moviedb_44.data.source.remote.config.response.MovieResponse
import com.sun_asterisk.moviedb_44.data.source.remote.config.response.ProducerResponse
import com.sun_asterisk.moviedb_44.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movie_id}/credits?api_key=" + BuildConfig.API_KEY)
    fun getListActors(@Path("movie_id") movieId: Int): Observable<ActorResponse>

    @GET("movie/latest?api_key=" + BuildConfig.API_KEY + Constant.language)
    fun getMovieLatest(): Observable<Movie>

    @GET("movie/popular?api_key=" + BuildConfig.API_KEY + Constant.language)
    fun getListMoviePopular(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/upcoming?api_key" + BuildConfig.API_KEY + Constant.language)
    fun getListMovieUpcoming(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/{movie_id}/credits?api_key=" + BuildConfig.API_KEY)
    fun getListProducers(@Path("movie_id") movieId: Int): Observable<ProducerResponse>

    @GET("movie/top_rated?api_key=" + BuildConfig.API_KEY + Constant.language)
    fun getListMovieTopRated(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/now_playing?api_key=" + BuildConfig.API_KEY + Constant.language)
    fun getListMovieNowPlaying(@Query("page") page: Int): Observable<MovieResponse>
}
