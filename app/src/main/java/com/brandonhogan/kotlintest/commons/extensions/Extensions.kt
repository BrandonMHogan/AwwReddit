package com.brandonhogan.kotlintest.commons.extensions

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.brandonhogan.kotlintest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy


/**
 * Created by Brandon on 2/1/2017.
 * Description :
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String, gifUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .asBitmap()
                .placeholder(R.drawable.progress_anim)
                .skipMemoryCache(true)
                .into(this)
    } else {

        Glide.with(context)
                .load(imageUrl)
                .asBitmap()
                .placeholder(R.drawable.progress_anim)
                .fitCenter()
                .skipMemoryCache(true)
                .into(this)
    }
}

fun ImageView.preloadGif(gifUrl: String) {
        Glide.with(context)
            .load(gifUrl)
            .preload(500, 500)
}

fun ImageView.loadGif(imageUrl: String) {

    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(Priority.IMMEDIATE)
                .placeholder(R.drawable.progress_anim)
                .fitCenter()
                .crossFade()
                .skipMemoryCache(true)
                .into(this)
    } else {
        Glide.with(context)
                .load(imageUrl)
                .asGif()
                .priority(Priority.IMMEDIATE)
                .placeholder(R.drawable.progress_anim)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .crossFade()
                .skipMemoryCache(true)
                .into(this)
    }
}

// Inline function to create Parcel Creator
inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }