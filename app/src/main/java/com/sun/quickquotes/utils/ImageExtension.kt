package com.sun.quickquotes.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sun.quickquotes.R

@BindingAdapter("app:srcCorner")
fun ImageView.loadImageCorners(@DrawableRes drawable: Int) {
    Glide.with(context)
        .load(drawable)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(R.dimen.dp_14)))
        .error(R.drawable.ic_error)
        .placeholder(R.drawable.ic_holder)
        .into(this)
}

@BindingAdapter("app:loadImageFavorite")
fun ImageView.loadImageFavorite(isFavorite: Boolean) {
    if (isFavorite) {
        Glide.with(context).load(R.drawable.ic_favorite).into(this)
    } else {
        Glide.with(context).load(R.drawable.ic_unfavorite).into(this)
    }
}
