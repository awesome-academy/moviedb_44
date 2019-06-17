package com.sun_asterisk.moviedb_44.screen.favorite

import androidx.databinding.ObservableField
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.screen.base.BaseViewModel
import com.sun_asterisk.moviedb_44.screen.search.adapter.MovieHorizontalAdapter
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel constructor(
    private val movieRepository: MovieRepository,
    listener: OnItemRecyclerViewClickListener<Movie>
) : BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val adapter = ObservableField<MovieHorizontalAdapter>()

    init {
        adapter.set(MovieHorizontalAdapter(listener))
    }

    private fun getData() {
        compositeDisposable.add(
            movieRepository.getAllMoviesFavorite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies -> adapter.get()!!.replaceItems(movies) },
                    { throwable -> throwable.localizedMessage })
        )
    }

    override fun onStart() {
        getData()
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}
