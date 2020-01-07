package com.architectcoders.generic.ui.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(private var items: MutableList<T>,
                              private val listener: (T) -> Unit)
    : RecyclerView.Adapter<BaseAdapter.Holder<T>>() {

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<T>

    override fun onBindViewHolder(holder: Holder<T>, position: Int) {
        val item = items[position]
        holder.bind(this, item)
    }

    fun addItems(items: MutableList<T>) {
        val start = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(start, items.size)
    }

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    override fun getItemCount() = items.size

    fun click(item: T) {
        listener(item)
    }

    abstract class Holder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(adapter: BaseAdapter<T>, item: T) = with(itemView) {
            bindItem(item)
            initListener(adapter, item)
        }

        abstract fun View.bindItem(item: T)

        private fun View.initListener(adapter: BaseAdapter<T>, item: T) {
            setOnClickListener {
                adapter.click(item)
            }
        }
    }
}
