package com.sun_asterisk.moviedb_44.screen.home.adapter

import androidx.databinding.ViewDataBinding
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.databinding.ItemMovieBinding
import com.sun_asterisk.moviedb_44.screen.base.BaseAdapter
import com.sun_asterisk.moviedb_44.screen.base.BaseViewHolder
import com.sun_asterisk.moviedb_44.screen.home.ItemMovieViewModel
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class MovieAdapter constructor(private val listener: OnItemRecyclerViewClickListener<Movie>) : BaseAdapter<Movie>(){

    override fun layout(row: Int): Int = R.layout.item_movie

    override fun viewHolder(
        binding: ViewDataBinding): BaseViewHolder<Movie> = MovieAdapterViewHolder(
        binding as ItemMovieBinding, listener)

    companion object {

        class MovieAdapterViewHolder(private val itemMovieBinding: ItemMovieBinding, private val listener: OnItemRecyclerViewClickListener<Movie>) :
            BaseViewHolder<Movie>(itemMovieBinding) {

            override fun bindData(data: Movie) {
                itemMovieBinding.viewModel = ItemMovieViewModel(listener)
                itemMovieBinding.viewModel?.setData(data)
                itemMovieBinding.executePendingBindings()
            }
        }
    }
}
