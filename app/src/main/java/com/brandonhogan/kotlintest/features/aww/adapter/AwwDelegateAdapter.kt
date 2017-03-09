package com.brandonhogan.kotlintest.features.aww.adapter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.BusStation
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.commons.adapter.ViewType
import com.brandonhogan.kotlintest.commons.adapter.ViewTypeDelegateAdapter
import com.brandonhogan.kotlintest.commons.extensions.getFriendlyTime
import com.brandonhogan.kotlintest.commons.extensions.inflate
import com.brandonhogan.kotlintest.commons.extensions.loadImg
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.aww_item.view.*

class AwwDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditAwwItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.aww_item)) {

        fun bind(item: RedditAwwItem) = with(itemView) {

            val myListener = View.OnClickListener {
                Log.d("AwwDelegateAdapter", "Image Clicked, passing back to main activity")
                BusStation.bus.post(item)
            }


            val requestListener = object : RequestListener<String, Bitmap> {

                override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                    Log.e("AwwDelegateAdapter", "Error on Glide Load : { e?.message }")
                    return false
                }

                override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                    progress.visibility = View.GONE
                    return false
                }

            }

            progress.visibility = View.VISIBLE
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            img_thumbnail.loadImg(item.image, item.gif, requestListener)
            title.text = item.title
            author.text = item.author
            time.text = item.created.getFriendlyTime()

            img_thumbnail.setOnClickListener(myListener)
        }
    }
}