package com.sun_asterisk.moviedb_44.data.model

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_44.utils.Constant

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String
) {

    companion object {
        private const val TOTAL_RATE_API = 10
        private const val TOTAL_RATE_APP_MOBILE = 5
    }

    fun getCustomVoteAverage(): Float = (voteAverage / TOTAL_RATE_API) * TOTAL_RATE_APP_MOBILE

    fun getUrlBackDrop(): String = Constant.BASE_BACKDROP_PATH + backdropPath

    fun getUrlPoster(): String = Constant.BASE_POSTER_PATH + backdropPath
}
