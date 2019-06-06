package com.sun_asterisk.moviedb_44.screen.detail.adapter

import androidx.databinding.ViewDataBinding
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Producer
import com.sun_asterisk.moviedb_44.databinding.ItemProducerBinding
import com.sun_asterisk.moviedb_44.screen.base.BaseAdapter
import com.sun_asterisk.moviedb_44.screen.base.BaseViewHolder
import com.sun_asterisk.moviedb_44.screen.detail.ItemProducerViewModel
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class ProducerAdapter(private val listener: OnItemRecyclerViewClickListener<Int>) : BaseAdapter<Producer>() {
    override fun layout(row: Int): Int = R.layout.item_producer

    override fun viewHolder(binding: ViewDataBinding): BaseViewHolder<Producer> {
        return ProducerViewModel(binding as ItemProducerBinding, listener)
    }

    companion object {
        class ProducerViewModel(
            private val binding: ItemProducerBinding,
            private val listener: OnItemRecyclerViewClickListener<Int>) : BaseViewHolder<Producer>(binding) {
            override fun bindData(data: Producer) {
                binding.viewModel = ItemProducerViewModel(listener)
                binding.viewModel?.setData(data)
                binding.executePendingBindings()
            }
        }
    }
}
