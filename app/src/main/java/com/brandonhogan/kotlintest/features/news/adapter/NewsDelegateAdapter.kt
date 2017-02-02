package com.brandonhogan.kotlintest.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.RedditNewsItem
import com.brandonhogan.kotlintest.commons.adapter.ViewType
import com.brandonhogan.kotlintest.commons.adapter.ViewTypeDelegateAdapter
import com.brandonhogan.kotlintest.commons.extensions.getFriendlyTime
import com.brandonhogan.kotlintest.commons.extensions.inflate
import com.brandonhogan.kotlintest.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}