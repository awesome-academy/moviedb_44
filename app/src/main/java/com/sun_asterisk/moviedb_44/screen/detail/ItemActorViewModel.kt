package com.sun_asterisk.moviedb_44.screen.detail

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun_asterisk.moviedb_44.BR
import com.sun_asterisk.moviedb_44.data.model.Actor
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class ItemActorViewModel (private val listener:OnItemRecyclerViewClickListener<Int>? = null) : BaseObservable() {
    @Bindable
    var actor: Actor? = null

    fun setData(data: Actor?) {
        data?.let {
            actor = it
            notifyPropertyChanged(BR.actor)
        }
    }

    fun onItemClick() {
        actor?.let { listener?.onItemClick(it.id) }
    }
}
