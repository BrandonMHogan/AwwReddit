package com.brandonhogan.kotlintest.api

import retrofit2.Call

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}