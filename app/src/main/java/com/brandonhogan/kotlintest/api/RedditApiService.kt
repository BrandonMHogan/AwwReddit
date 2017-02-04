package com.brandonhogan.kotlintest.api

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApiService {

    @GET("/r/aww/hot.json")
    fun getAwwHot(@Query("after") after: String,
                  @Query("limit") limit: String): Call<RedditAwwResponse>

    @GET("/r/aww/top.json")
    fun getAwwTop(@Query("after") after: String,
                  @Query("limit") limit: String): Call<RedditAwwResponse>
}