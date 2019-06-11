package com.sun_asterisk.moviedb_44.screen.search

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.screen.base.BaseViewModel
import com.sun_asterisk.moviedb_44.screen.search.adapter.MovieHorizontalAdapter
import com.sun_asterisk.moviedb_44.utils.Constant
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val movieRepository: MovieRepository, listener: OnItemRecyclerViewClickListener<Movie>) : BaseViewModel() {
    private var page = Constant.PAGE_DEFAULT
    private var oldSearchContent: String = ""
    private var newSearchContent: String = ""
    val topProgressBarObservable: ObservableBoolean = ObservableBoolean()
    val bottomProgressBarObservable: ObservableBoolean = ObservableBoolean()
    val announceObservable: ObservableBoolean = ObservableBoolean()
    val recyclerViewObservable: ObservableBoolean = ObservableBoolean()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val movieAdapter = ObservableField<MovieHorizontalAdapter>()
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            newSearchContent = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            announceObservable.set(false)
        }
    }

    init {
        movieAdapter.set(MovieHorizontalAdapter(listener))
        setupOriginal()
    }

    private fun setupOriginal() {
        announceObservable.set(false)
        recyclerViewObservable.set(false)
        topProgressBarObservable.set(false)
    }

    fun initData() {
        page = Constant.PAGE_DEFAULT
        if (newSearchContent != oldSearchContent) {
            getData()
        } else {
            recyclerViewObservable.set(true)
        }
    }

    fun loadMore() {
        if ((movieAdapter.get()!!.itemCount % Constant.AMOUNT_ITEM_IN_PER_PAGE) == 0) {
            page++
            getData()
        }
    }

    private fun getData() {
        compositeDisposable.add(
            movieRepository.searchMovie(newSearchContent, page, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    announceObservable.set(false)
                    if (page == Constant.PAGE_DEFAULT) topProgressBarObservable.set(true)
                    else bottomProgressBarObservable.set(true)
                }
                .doOnTerminate {
                    topProgressBarObservable.set(false)
                    bottomProgressBarObservable.set(false)
                }
                .subscribe(
                    { movies ->
                        if (movies.isNotEmpty()) { // movies always not null
                            onSearchSuccess(movies)
                        } else {
                            onNoMovie()
                        }
                    }, { throwable -> throwable.localizedMessage })
        )
    }

    private fun onNoMovie() {
        announceObservable.set(true)
        recyclerViewObservable.set(false)
    }

    private fun onSearchSuccess(movies: MutableList<Movie>) {
        if (page == Constant.PAGE_DEFAULT)
            movieAdapter.get()!!.clearList()
        oldSearchContent = newSearchContent
        movieAdapter.get()!!.addItems(movies)
        recyclerViewObservable.set(true)
    }

    override fun onStart() {
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}
