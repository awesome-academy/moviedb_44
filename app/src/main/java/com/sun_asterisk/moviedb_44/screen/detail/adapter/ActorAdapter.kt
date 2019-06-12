package com.sun_asterisk.moviedb_44.screen.detail.adapter

import androidx.databinding.ViewDataBinding
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Actor
import com.sun_asterisk.moviedb_44.databinding.ItemActorBinding
import com.sun_asterisk.moviedb_44.screen.base.BaseAdapter
import com.sun_asterisk.moviedb_44.screen.base.BaseViewHolder
import com.sun_asterisk.moviedb_44.screen.detail.ItemActorViewModel
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class ActorAdapter constructor(private val listener: OnItemRecyclerViewClickListener<Int>) : BaseAdapter<Actor>() {

    override fun layout(row: Int): Int = R.layout.item_actor

    override fun viewHolder(binding: ViewDataBinding): BaseViewHolder<Actor> {
        return ActorViewHolder(binding as ItemActorBinding, listener)
    }

    companion object {
        class ActorViewHolder(
            private val binding: ItemActorBinding,
            private val listener: OnItemRecyclerViewClickListener<Int>) : BaseViewHolder<Actor>(binding) {
            override fun bindData(data: Actor) {
                binding.viewModel = ItemActorViewModel(listener)
                binding.viewModel?.setData(data)
                binding.executePendingBindings()
            }
        }
    }
}
