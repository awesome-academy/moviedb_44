package com.sun_asterisk.moviedb_44.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object BindingUtils {
    private const val THUMBNAIL_SIZE_MULTIPLIER = 0.1f

    @BindingAdapter("adapter")
    @JvmStatic
    fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }

    @BindingAdapter(value = ["imgUrl", "bind:type"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, path: String?, type: Int = 0) {
        val url: String = if (type == 1)
            Constant.BASE_BACKDROP_PATH + path
         else Constant.BASE_POSTER_PATH + path

        Glide.with(imageView.context).load(url).thumbnail(THUMBNAIL_SIZE_MULTIPLIER)
            .into(imageView)
    }

    @BindingAdapter("imgUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, bitmap: Bitmap) {
        Glide.with(imageView.context).load(bitmap).thumbnail(THUMBNAIL_SIZE_MULTIPLIER)
            .into(imageView)
    }
}
