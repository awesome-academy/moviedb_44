package com.sun_asterisk.moviedb_44.units

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

    @BindingAdapter("imgUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String) {
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
