package com.brandonhogan.kotlintest.features.news

import com.brandonhogan.kotlintest.api.NewsAPI
import com.brandonhogan.kotlintest.api.NewsRestAPI
import com.brandonhogan.kotlintest.commons.RedditNews
import com.brandonhogan.kotlintest.commons.RedditNewsItem
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

@Singleton
class NewsManager @Inject constructor(private val api: NewsAPI) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
                val redditNews = RedditNews(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news)

                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}