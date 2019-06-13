package com.sun_asterisk.moviedb_44.screen.detail

import androidx.databinding.ObservableField
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.screen.base.BaseViewModel
import com.sun_asterisk.moviedb_44.screen.detail.adapter.ActorAdapter
import com.sun_asterisk.moviedb_44.screen.detail.adapter.ProducerAdapter
import com.sun_asterisk.moviedb_44.utils.OnItemRecyclerViewClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel constructor(
    private val movieRepository: MovieRepository,
    listener: OnItemRecyclerViewClickListener<Int>) : BaseViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val actorAdapter = ObservableField<ActorAdapter>()
    val producerAdapter = ObservableField<ProducerAdapter>()

    init {
        actorAdapter.set(ActorAdapter(listener))
        producerAdapter.set(ProducerAdapter(listener))
    }

    fun getActorAndProducer(idMovie: Int) {
        compositeDisposable.add(
            movieRepository.getActors(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ actors -> actorAdapter.get()?.replaceItems(actors) },
                    { throwable -> throwable.localizedMessage })
        )

        compositeDisposable.add(
            movieRepository.getProducers(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({producers -> producerAdapter.get()!!.replaceItems(producers)},
                    { throwable -> throwable.localizedMessage })
        )
    }

    override fun onStart() {
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}