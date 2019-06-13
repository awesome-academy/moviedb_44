package com.sun_asterisk.moviedb_44.data.source.local.config.dao

import androidx.room.*
import com.sun_asterisk.moviedb_44.data.model.Movie
import io.reactivex.Flowable

@Dao
interface MovieDAO {
    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM movies WHERE id = :idMovie) THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END")
    fun isMovieFavorite(idMovie: Int): Flowable<Boolean>

    @Query("SELECT * FROM movies")
    fun getAllMoviesFavorite(): Flowable<MutableList<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieFavorite(movie: Movie)

    @Delete
    fun deleteMovieFavorite(movie: Movie)
}
