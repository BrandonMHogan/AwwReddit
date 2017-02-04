package com.brandonhogan.kotlintest.api

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

import retrofit2.Call
import javax.inject.Inject

class AwwAPIImpl @Inject constructor(private val awwApiService: AwwApiService) : AwwAPI {

    override fun getAwwHot(after: String, limit: String): Call<RedditAwwResponse> {
        return awwApiService.getAwwHot(after, limit)
    }

    override fun getAwwTop(after: String, limit: String): Call<RedditAwwResponse> {
        return awwApiService.getAwwTop(after, limit)
    }
}