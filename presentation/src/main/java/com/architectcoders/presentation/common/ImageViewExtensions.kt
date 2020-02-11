package com.architectcoders.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.architectcoders.generic.framework.extension.view.loadUrl

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl(url)
}