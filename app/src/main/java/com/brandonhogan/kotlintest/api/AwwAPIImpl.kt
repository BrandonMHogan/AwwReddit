package com.brandonhogan.kotlintest.api

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

import retrofit2.Call
import javax.inject.Inject

class AwwAPIImpl @Inject constructor(private val redditApiService: RedditApiService) : AwwAPI {

    override fun getAwwHot(after: String, limit: String): Call<RedditAwwResponse> {
        return redditApiService.getAwwHot(after, limit)
    }

    override fun getAwwTop(after: String, limit: String): Call<RedditAwwResponse> {
        return redditApiService.getAwwTop(after, limit)
    }
}