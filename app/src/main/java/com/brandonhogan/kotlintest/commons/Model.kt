package com.brandonhogan.kotlintest.commons

import com.brandonhogan.kotlintest.commons.adapter.AdapterConstants
import com.brandonhogan.kotlintest.commons.adapter.ViewType

/**
 * Created by Brandon on 2/1/2017.
 * Description :
 */

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}