package com.architectcoders.equipocinco.ui.fragment.detail

import androidx.databinding.BindingAdapter
import com.architectcoders.equipocinco.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("favorite")
fun FloatingActionButton.setFavorite(favorite: Boolean?) {
    val icon = if (favorite == true) R.drawable.ic_star else R.drawable.ic_star_border
    setImageDrawable(context.getDrawable(icon))
}