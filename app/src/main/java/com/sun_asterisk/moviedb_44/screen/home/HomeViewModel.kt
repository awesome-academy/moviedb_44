package com.sun_asterisk.moviedb_44.screen.home

import androidx.databinding.ObservableField
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.screen.base.BaseViewModel
import com.sun_asterisk.moviedb_44.screen.home.adapter.MovieAdapter
import com.sun_asterisk.moviedb_44.utils.Constant
import com.sun_asterisk.moviedb_44.utils.OnItemBannerClickListener
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel constructor(
    private val movieRepository: MovieRepository,
    itemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Movie>,
    itemBannerListener: OnItemBannerClickListener<Movie>) : BaseViewModel() {

    val bannerObservable = ObservableField<BannerViewModel>()
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    val popularAdapter = ObservableField<MovieAdapter>()
    val upComingAdapter = ObservableField<MovieAdapter>()
    val playNowAdapter = ObservableField<MovieAdapter>()

    init {
        popularAdapter.set(MovieAdapter(itemRecyclerViewClickListener))
        upComingAdapter.set(MovieAdapter(itemRecyclerViewClickListener))
        playNowAdapter.set(MovieAdapter(itemRecyclerViewClickListener))
        bannerObservable.set(BannerViewModel(itemBannerListener))
    }

    override fun onStart() {
        initData()
    }

    private fun initData() {
        mCompositeDisposable.add(
            movieRepository
                .getListMovieTopRated(Constant.PAGE_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movie -> bannerObservable.get()!!.setData(movie[0]) },
                    { throwable -> throwable.localizedMessage })
        )

        mCompositeDisposable.add(
            movieRepository
                .getListMoviePopular(Constant.PAGE_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies -> popularAdapter.get()!!.replaceItems(movies) },
                    { throwable -> throwable.localizedMessage })
        )

        mCompositeDisposable.add(
            movieRepository
                .getListMovieNowPlaying(Constant.PAGE_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies -> playNowAdapter.get()!!.replaceItems(movies) },
                    { throwable -> throwable.localizedMessage })
        )

        mCompositeDisposable.add(
            movieRepository
                .getListMovieUpcoming(Constant.PAGE_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies -> upComingAdapter.get()!!.replaceItems(movies) },
                    { throwable -> throwable.localizedMessage })
        )
    }

    fun refreshData() {
        initData()
    }

    override fun onStop() {
        mCompositeDisposable.clear()
    }
}
