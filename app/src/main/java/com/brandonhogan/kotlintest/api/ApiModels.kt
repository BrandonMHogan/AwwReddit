package com.brandonhogan.kotlintest.api

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

class RedditAwwResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChildrenResponse(val data: RedditAwwDataResponse)

class RedditAwwDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)