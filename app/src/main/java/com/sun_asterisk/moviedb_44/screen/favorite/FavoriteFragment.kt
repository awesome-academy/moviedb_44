package com.sun_asterisk.moviedb_44.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_44.data.source.local.config.dao.MovieDatabase
import com.sun_asterisk.moviedb_44.data.source.remote.MovieRemoteDataSource
import com.sun_asterisk.moviedb_44.databinding.FragmentFavoriteBinding
import com.sun_asterisk.moviedb_44.screen.base.BaseFragment
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class FavoriteFragment : BaseFragment(), OnItemRecyclerViewClickListener<Movie> {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        val movieRepository = MovieRepository.getInstance(
            MovieLocalDataSource.getInstance(MovieDatabase.getInstance(context!!).movieDAO()),
            MovieRemoteDataSource.getInstance())
        viewModel = FavoriteViewModel(movieRepository, this)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onItemClick(data: Movie) {
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    companion object {
        fun getInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}
