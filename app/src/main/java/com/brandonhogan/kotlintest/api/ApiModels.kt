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
        val preview: RedditPreviewDataResponse?,
        val author: String,
        val title: String,
        val created_utc: Long,
        val url: String
)

class RedditPreviewDataResponse(
        val images: List<RedditImagesDataResponse>?
)

class RedditImagesDataResponse (
    val source: RedditSourceDataResponse?
)

class RedditSourceDataResponse(
        val url: String
)