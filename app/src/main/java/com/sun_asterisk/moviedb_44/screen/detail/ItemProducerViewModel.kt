package com.sun_asterisk.moviedb_44.screen.detail

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun_asterisk.moviedb_44.BR
import com.sun_asterisk.moviedb_44.data.model.Producer
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class ItemProducerViewModel(private val listener: OnItemRecyclerViewClickListener<Int>? = null): BaseObservable(){
    @Bindable
    var producer: Producer? = null

    fun setData(data : Producer) {
        data?.let {
            producer = it
            notifyPropertyChanged(BR.producer)
        }
    }

    fun onItemClick() {
        producer?.let { listener?.onItemClick(it.id) }
    }
}
