package com.sun_asterisk.moviedb_44.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_44.utils.Constant

@Entity(tableName = "movies", indices = [Index("id")])
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Movie> {
        private const val TOTAL_RATE_API = 10
        private const val TOTAL_RATE_APP_MOBILE = 5
        private const val NUMBER_ROUND_TWO_DIGITS = 100.0

        override fun createFromParcel(parcel: Parcel): Movie = Movie(parcel)

        override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
    }

    fun getCustomVoteAverage(): Float = (Math.round(
        (voteAverage / TOTAL_RATE_API) * TOTAL_RATE_APP_MOBILE * NUMBER_ROUND_TWO_DIGITS)
        / NUMBER_ROUND_TWO_DIGITS).toFloat()

    fun getStringVoteCount(): String = voteCount.toString() + Constant.RATING

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeInt(id)
        writeString(title)
        writeFloat(voteAverage)
        writeInt(voteCount)
        writeString(posterPath)
        writeString(backdropPath)
        writeString(overview)
        writeString(releaseDate)
    }

    override fun describeContents(): Int = 0
}
