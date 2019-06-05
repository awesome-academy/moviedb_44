package com.sun_asterisk.moviedb_44.screen.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindData(data: T)

    fun view() = binding.root
}
