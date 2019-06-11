package com.sun_asterisk.moviedb_44.screen.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_44.data.source.remote.MovieRemoteDataSource
import com.sun_asterisk.moviedb_44.databinding.ActivitySearchBinding
import com.sun_asterisk.moviedb_44.utils.EndlessRecyclerOnScrollListener
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener
import com.sun_asterisk.moviedb_44.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), OnItemRecyclerViewClickListener<Movie>, View.OnClickListener {

    lateinit var viewModel: SearchViewModel
    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = SearchViewModel(MovieRepository.getInstance(MovieLocalDataSource(),
                MovieRemoteDataSource.getInstance()), this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel
        binding.imgBack.setOnClickListener(this)
        addEvent()
    }

    private fun addEvent() {
        binding.edInput.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE && edInput.text.toString().trim().isNotEmpty()) {
                    hideKeyboard(this@SearchActivity)
                    viewModel.initData()
                    return true
                }
                return false
            }
        })

        binding.include.recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                viewModel.loadMore()
            }
        })
    }

    override fun onItemClick(data: Movie) {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imgBack -> onBackPressed()
        }
    }

    companion object {
        fun getSearchProfile(context: Context): Intent = Intent(context, SearchActivity::class.java)
    }
}
