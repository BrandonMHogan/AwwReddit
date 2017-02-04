package com.brandonhogan.kotlintest.features.aww.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.commons.adapter.AdapterConstants
import com.brandonhogan.kotlintest.commons.adapter.ViewType
import com.brandonhogan.kotlintest.commons.adapter.ViewTypeDelegateAdapter
import java.util.*

class AwwAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.ITEM, AwwDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addNews(aNews: List<RedditAwwItem>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert aNews and the loading at the end of the list
        items.addAll(aNews)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddNews(aNews: List<RedditAwwItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(aNews)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<RedditAwwItem> {
        return items
                .filter { it.getViewType() == AdapterConstants.ITEM }
                .map { it as RedditAwwItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}