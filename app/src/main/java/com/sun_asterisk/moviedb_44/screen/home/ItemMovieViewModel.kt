package com.sun_asterisk.moviedb_44.screen.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun_asterisk.moviedb_44.BR
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class ItemMovieViewModel(
    private val listener: OnItemRecyclerViewClickListener<Movie>? = null) : BaseObservable() {

    @Bindable
    var movie: Movie? = null

    fun setData(data: Movie?) {
        data?.let {
            movie = it
            notifyPropertyChanged(BR.movie)
        }
    }

    fun onItemClick() {
        movie?.let {
            listener?.onItemClick(it)
        }
    }
}
