package com.sun_asterisk.moviedb_44.utils

import androidx.annotation.StringDef

@StringDef(CategoryAnnotation.POPULAR, CategoryAnnotation.NOW_PLAYING, CategoryAnnotation.UPCOMING)
annotation class CategoryAnnotation {
    companion object {
        const val POPULAR = "Popular"
        const val NOW_PLAYING = "Now Playing"
        const val UPCOMING = "Upcoming"
    }
}
