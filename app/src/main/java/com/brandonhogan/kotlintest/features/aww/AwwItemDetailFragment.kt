package com.brandonhogan.kotlintest.features.aww

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import com.brandonhogan.kotlintest.MainActivity
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.BaseFragment
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.commons.extensions.inflate
import javax.inject.Inject
import kotlinx.android.synthetic.main.aww_item_detail_fragment.*
import android.webkit.WebChromeClient


/**
 * Created by Brandon on 2/5/2017.
 * Description :
 */

class AwwItemDetailFragment : BaseFragment() {

    companion object {
        @JvmStatic val AWW_ITEM: String = "AwwItem"

        @JvmStatic fun newInstance(redditAwwItem: RedditAwwItem): AwwItemDetailFragment {
            val awwItemDetailFragment: AwwItemDetailFragment = AwwItemDetailFragment()
            val args: Bundle = Bundle()
            args.putParcelable(AWW_ITEM, redditAwwItem)
            awwItemDetailFragment.arguments = args
            return awwItemDetailFragment
        }
    }

    @Inject lateinit var awwManager: AwwManager

    private var redditAwwItem: RedditAwwItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle = this.arguments
        if (bundle != null)
            redditAwwItem = bundle.getParcelable(AWW_ITEM)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.aww_item_detail_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).setActionBarTitle(redditAwwItem?.title ?: "")

        val webSettings = web.settings
        webSettings.javaScriptEnabled = true
        webSettings.setSupportMultipleWindows(true)
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        // add progress bar
        web.setWebChromeClient(WebChromeClient())
        web.setWebViewClient(WebViewClient())
        web.setBackgroundColor(Color.TRANSPARENT)

        web.loadUrl(redditAwwItem?.url)

//        if (!redditAwwItem?.gif.isNullOrEmpty())
//            web.loadUrl(redditAwwItem?.gif)
//        else
//            web.loadUrl(redditAwwItem?.image)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
