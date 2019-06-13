package com.sun_asterisk.moviedb_44.data.source.local.config.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    companion object {
        private var sInstance: MovieDatabase? = null
        private const val DATABASE_NAME = "database_movie"

        @JvmStatic
        fun getInstance(context: Context) : MovieDatabase{
            synchronized(MovieRepository::class.java) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return sInstance!!
            }
        }
    }
}
