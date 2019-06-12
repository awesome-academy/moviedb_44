package com.sun_asterisk.moviedb_44.screen.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun_asterisk.moviedb_44.BR
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.utils.OnItemBannerClickListener

class BannerViewModel(private val listenerBanner: OnItemBannerClickListener<Movie>?) : BaseObservable() {
    @Bindable
    var movie: Movie? = null

    fun setData(data: Movie?) {
        data?.let {
            movie = it
            notifyPropertyChanged(BR.movie)
        }
    }

    fun onItemClick() {
        listenerBanner?.let { listenerBanner.onItemClick(movie!!) }
    }
}
