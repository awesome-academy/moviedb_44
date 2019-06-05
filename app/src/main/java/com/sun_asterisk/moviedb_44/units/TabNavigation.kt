package com.sun_asterisk.moviedb_44.units

import androidx.annotation.IntDef

@IntDef(TabNavigation.HOME, TabNavigation.GENRES, TabNavigation.FAVORITE)
annotation class TabNavigation {
    companion object {
        const val HOME = 0
        const val GENRES = 1
        const val FAVORITE = 2
    }
}
