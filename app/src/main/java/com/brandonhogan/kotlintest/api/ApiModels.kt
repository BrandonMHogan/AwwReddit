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

data class RedditAwwDataResponse(
        val author: String,
        val title: String,
        val created_utc: Long,
        val url: String,
        val resolutions : List<Resolutions>,
        val gifs : List<Resolutions>
)

data class Resolutions(
        val url: String,
        val width: Int,
        val height: Int
)