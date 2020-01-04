package com.architectcoders.equipocinco.framework

import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageButton
import com.architectcoders.generic.framework.extension.view.gone
import com.architectcoders.generic.framework.extension.view.setOnTextChangedListener
import com.architectcoders.generic.framework.extension.view.visible

class SearchManager(
    private val et: EditText,
    private val ib: ImageButton,
    private val listener: Listener
) {

    companion object {
        const val CHARS_SEARCH_LENGTH = 1
    }

    interface Listener {
        fun onTextChanged(query: String)
        fun onShowProgressBar(show: Boolean)
    }

    private var searchHandler: Handler = Handler(Looper.getMainLooper())

    init {
        et.clearFocus()
        et.setOnTextChangedListener { query ->
            search(query.toString())
        }
        ib.setOnClickListener {
            et.setText("")
        }
    }

    private fun search(s: String) {
        searchHandler.removeCallbacks(searchRunnable)
        val sTrimmed = s.trim()
        if (s.isEmpty()) {
            ib.gone()
        } else {
            ib.visible()
        }
        if (sTrimmed.isEmpty() || sTrimmed.length < CHARS_SEARCH_LENGTH) {
            listener.onShowProgressBar(false)
        } else if (sTrimmed.length >= CHARS_SEARCH_LENGTH) {
            listener.onShowProgressBar(true)
        }
        searchHandler.postDelayed(searchRunnable, 500)
    }

    private var searchRunnable = Runnable {
        val query = et.text.toString()
        listener.onTextChanged(query)
    }
}
