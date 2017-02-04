package com.brandonhogan.kotlintest.features.aww

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brandonhogan.kotlintest.AppController
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.BaseFragment
import com.brandonhogan.kotlintest.commons.InfiniteScrollListener
import com.brandonhogan.kotlintest.commons.RedditAww
import com.brandonhogan.kotlintest.commons.extensions.inflate
import com.brandonhogan.kotlintest.di.module.AppModule
import com.brandonhogan.kotlintest.features.aww.adapter.AwwAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class AwwListFragment : BaseFragment() {

    companion object {
        private val NEWS_KEY = "newsKey"
    }

    @Inject lateinit var awwManager: AwwManager

    private var redditAww: RedditAww? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppController.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(NEWS_KEY)) {
            redditAww = savedInstanceState.get(NEWS_KEY) as RedditAww
            (news_list.adapter as AwwAdapter).clearAndAddNews(redditAww!!.aNews)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as AwwAdapter).getNews()
        if (redditAww != null && news.isNotEmpty()) {
            outState.putParcelable(NEWS_KEY, redditAww?.copy(aNews = news))
        }
    }

    private fun requestNews() {
        /**
         * first time will send empty string for after parameter.
         * Next time we will have redditAww set with the next page to
         * navigate with the after param.
         */
        val subscription = awwManager.getRedditAww(redditAww?.after ?: "", "10", true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { retrievedNews ->
                            redditAww = retrievedNews
                            (news_list.adapter as AwwAdapter).addNews(retrievedNews.aNews)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = AwwAdapter()
        }
    }
}
