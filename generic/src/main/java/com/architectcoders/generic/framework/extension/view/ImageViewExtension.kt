package com.architectcoders.generic.framework.extension.view

import android.widget.ImageView
import com.architectcoders.generic.R
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).placeholder(R.drawable.place_holder).into(this)
}
