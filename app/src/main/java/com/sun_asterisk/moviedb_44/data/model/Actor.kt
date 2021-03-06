package com.sun_asterisk.moviedb_44.data.model

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("cast_id")
    val castId: Int,
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    @SerializedName("profile_path")
    val profilePath: String?
)
