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
import com.brandonhogan.kotlintest.features.aww.adapter.AwwAdapter
import kotlinx.android.synthetic.main.aww_list_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class AwwListFragment : BaseFragment() {

    companion object {
        private val AWW_KEY = "awwKey"
    }

    @Inject lateinit var awwManager: AwwManager

    private var redditAww: RedditAww? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppController.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.aww_list_fragment)
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

        if (savedInstanceState != null && savedInstanceState.containsKey(AWW_KEY)) {
            redditAww = savedInstanceState.get(AWW_KEY) as RedditAww
            (news_list.adapter as AwwAdapter).clearAndAddNews(redditAww!!.aNews)
        } else {
            requestNews()
        }

        swipe_refresh_layout.setOnRefreshListener { requestNews() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as AwwAdapter).getNews()
        if (redditAww != null && news.isNotEmpty()) {
            outState.putParcelable(AWW_KEY, redditAww?.copy(aNews = news))
        }
    }

    private fun requestNews() {
        val subscription = awwManager.getRedditAww(redditAww?.after ?: "", "40", true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { retrievedNews ->
                            redditAww = retrievedNews
                            (news_list.adapter as AwwAdapter).addNews(retrievedNews.aNews)
                            swipe_refresh_layout.isRefreshing = false
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_INDEFINITE).show()
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
