package com.brandonhogan.kotlintest.features.aww

import com.brandonhogan.kotlintest.api.AwwAPI
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

                    //TODO: check to see if its even possible for source to be null
                    var img : String = ""
                    if (item.preview != null && item.preview.images != null) {
                        img = item.preview.images.first().source?.url!!.replace("&amp;s", "&s")
                    }

                    RedditAwwItem(item.author, item.title,
                            item.created, img, item.url)

                }


                val redditNews = RedditAww(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news.filter { it.thumbnail.isNotEmpty() && !it.url.contains("reddit.com", true) })

                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}