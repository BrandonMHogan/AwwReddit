package com.brandonhogan.kotlintest.features.aww

import com.brandonhogan.kotlintest.api.AwwAPI
import com.brandonhogan.kotlintest.api.AwwAPIImpl
import com.brandonhogan.kotlintest.commons.RedditAww
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

@Singleton
class AwwManager @Inject constructor(private val api: AwwAPI) {

    fun getRedditAww(after: String, limit: String = "10", showHot: Boolean = true): Observable<RedditAww> {
        return Observable.create {
            subscriber ->
            val callResponse = if(showHot) { api.getAwwHot(after, limit) } else { api.getAwwTop(after, limit) }
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditAwwItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
                val redditNews = RedditAww(
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