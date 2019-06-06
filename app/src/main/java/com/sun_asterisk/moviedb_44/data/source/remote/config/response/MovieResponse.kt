package com.sun_asterisk.moviedb_44.data.source.remote.config.response

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_44.data.model.Movie

data class MovieResponse(
    @SerializedName("results")
    val movieList: MutableList<Movie> = mutableListOf()
)
