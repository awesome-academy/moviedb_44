package com.sun_asterisk.moviedb_44.data.source.remote.config.response

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_44.data.model.Actor

data class ActorResponse(
    @SerializedName("cast")
    val listActor: MutableList<Actor> = mutableListOf()
)
