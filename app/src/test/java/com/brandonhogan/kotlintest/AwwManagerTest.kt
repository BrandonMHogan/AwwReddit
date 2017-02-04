package com.brandonhogan.kotlintest

/**
 * Created by Brandon on 2/2/2017.
 * Description :
 */

import com.brandonhogan.kotlintest.api.*
import com.brandonhogan.kotlintest.commons.RedditAww
import com.brandonhogan.kotlintest.features.aww.AwwManager
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import retrofit2.Call
import retrofit2.Response
import rx.observers.TestSubscriber
import java.util.*


class AwwManagerTest {

    var testSub = TestSubscriber<RedditAww>()
    var apiMock = mock<AwwAPI>()
    var callMock = mock<Call<RedditAwwResponse>>()

    @Before
    fun setup() {
        testSub = TestSubscriber<RedditAww>()
        apiMock = mock<AwwAPI>()
        callMock = mock<Call<RedditAwwResponse>>()
        `when`(apiMock.getAwwHot(anyString(), anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        // prepare
        val redditNewsResponse = RedditAwwResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = AwwManager(apiMock)
        newsManager.getRedditAww("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()
    }

    @Test
    fun testSuccess_checkOneAww() {
        // prepare
        val newsData = RedditAwwDataResponse(
                "author",
                "title",
                10,
                Date().time,
                "thumbnail",
                "url"
        )
        val newsResponse = RedditChildrenResponse(newsData)
        val redditNewsResponse = RedditAwwResponse(RedditDataResponse(listOf(newsResponse), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = AwwManager(apiMock)
        newsManager.getRedditAww("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()

        assert(testSub.onNextEvents[0].aNews[0].author == newsData.author)
        assert(testSub.onNextEvents[0].aNews[0].title == newsData.title)
    }

    @Test
    fun testError() {
        // prepare
        val responseError = Response.error<RedditAwwResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))

        `when`(callMock.execute()).thenReturn(responseError)

        // call
        val newsManager = AwwManager(apiMock)
        newsManager.getRedditAww("").subscribe(testSub)

        // assert
        assert(testSub.onErrorEvents.size == 1)
    }
}

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)