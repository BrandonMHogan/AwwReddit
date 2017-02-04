package com.brandonhogan.kotlintest.api

import retrofit2.Call

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

interface AwwAPI {
    fun getAwwHot(after: String, limit: String): Call<RedditAwwResponse>
    fun getAwwTop(after: String, limit: String): Call<RedditAwwResponse>
}