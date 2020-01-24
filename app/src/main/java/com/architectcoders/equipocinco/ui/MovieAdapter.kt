package com.architectcoders.equipocinco.ui

import android.view.View
import android.view.ViewGroup
import com.architectcoders.equipocinco.R
import com.architectcoders.generic.framework.extension.view.inflate
import com.architectcoders.generic.framework.extension.view.loadUrl
import com.architectcoders.generic.ui.view.adapter.BaseAdapter
import com.architectcoders.domain.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    items: MutableList<Movie>,
    listener: (Movie) -> Unit
) : BaseAdapter<Movie>(items, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_movie))
    }

    class Holder(itemView: View) : BaseAdapter.Holder<Movie>(itemView) {
        override fun View.bindItem(item: Movie) {
            initIv(item)
        }

        private fun View.initIv(item: Movie) {
            val url = "https://image.tmdb.org/t/p/w185/${item.posterPath}"
            iv.loadUrl(url)
        }
    }
}
