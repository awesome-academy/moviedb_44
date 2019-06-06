package com.sun_asterisk.moviedb_44.screen.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: DetailViewModel
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = intent.getParcelableExtra(EXTRA_MOVIE)
        binding = setContentView(this, R.layout.activity_detail)
        binding.movie = movie
    }

    companion object {
        private const val EXTRA_MOVIE = "com.sun_asterisk.moviedb_44.extra.EXTRA_MOVIE"

        fun getIntentDetail(context: Context, movie: Movie): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }
    }
}
