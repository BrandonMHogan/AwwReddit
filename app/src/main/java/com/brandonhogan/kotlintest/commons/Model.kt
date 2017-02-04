package com.brandonhogan.kotlintest.commons

import android.os.Parcel
import android.os.Parcelable
import com.brandonhogan.kotlintest.commons.adapter.AdapterConstants
import com.brandonhogan.kotlintest.commons.adapter.ViewType

/**
 * Created by Brandon on 2/1/2017.
 * Description :
 */

data class RedditAww(
        val after: String,
        val before: String,
        val aNews: List<RedditAwwItem>) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RedditAww> = object : Parcelable.Creator<RedditAww> {
            override fun createFromParcel(source: Parcel): RedditAww = RedditAww(source)
            override fun newArray(size: Int): Array<RedditAww?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.createTypedArrayList(RedditAwwItem.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(after)
        dest?.writeString(before)
        dest?.writeTypedList(aNews)
    }
}

data class RedditAwwItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String?
) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.ITEM

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RedditAwwItem> = object : Parcelable.Creator<RedditAwwItem> {
            override fun createFromParcel(source: Parcel): RedditAwwItem = RedditAwwItem(source)
            override fun newArray(size: Int): Array<RedditAwwItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readInt(), source.readLong(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)
    }
}