package com.sun_asterisk.moviedb_44.screen.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_44.data.source.local.config.dao.MovieDatabase
import com.sun_asterisk.moviedb_44.data.source.remote.MovieRemoteDataSource
import com.sun_asterisk.moviedb_44.databinding.ActivityDetailBinding
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener

class DetailActivity : AppCompatActivity(), OnItemRecyclerViewClickListener<Int> {
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: DetailViewModel
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = intent.getParcelableExtra(EXTRA_MOVIE)
        viewModel = DetailViewModel(movie, MovieRepository.getInstance(
                MovieLocalDataSource.getInstance(MovieDatabase.getInstance(applicationContext).movieDAO()),
                MovieRemoteDataSource.getInstance()), this)
        binding = setContentView(this, R.layout.activity_detail)
        binding.movie = movie
        binding.viewModel = viewModel
        viewModel.getActorAndProducer(movie.id)
        binding.imgBookMark.setOnClickListener { viewModel.changeFavorite() }
        binding.imgBack.setOnClickListener { onBackPressed() }
    }

    override fun onItemClick(data: Int) {
    }

    companion object {
        private const val EXTRA_MOVIE = "com.sun_asterisk.moviedb_44.extra.EXTRA_MOVIE"

        fun getIntentDetail(context: Context, movie: Movie): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
