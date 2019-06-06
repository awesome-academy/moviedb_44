package com.sun_asterisk.moviedb_44.data.model

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_44.utils.Constant

data class Producer(
    @SerializedName("credit_id")
    val creditId: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)
