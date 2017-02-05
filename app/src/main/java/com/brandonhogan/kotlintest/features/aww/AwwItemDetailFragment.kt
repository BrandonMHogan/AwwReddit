package com.brandonhogan.kotlintest.features.aww

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.BaseFragment
import com.brandonhogan.kotlintest.commons.RedditAww
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.commons.extensions.inflate
import com.koushikdutta.ion.Ion
import javax.inject.Inject
import kotlinx.android.synthetic.main.aww_item_detail_fragment.*

/**
 * Created by Brandon on 2/5/2017.
 * Description :
 */

class AwwItemDetailFragment : BaseFragment() {

    companion object {
        @JvmStatic val AWW_ITEM : String = "AwwItem"

        @JvmStatic fun newInstance(redditAwwItem : RedditAwwItem) : AwwItemDetailFragment {
            val awwItemDetailFragment: AwwItemDetailFragment = AwwItemDetailFragment()
            val args : Bundle = Bundle()
            args.putParcelable(AWW_ITEM, redditAwwItem)
            awwItemDetailFragment.arguments = args
            return awwItemDetailFragment
        }
    }

    @Inject lateinit var awwManager: AwwManager

    private var redditAwwItem: RedditAwwItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle : Bundle = this.arguments
        if (bundle != null)
            redditAwwItem = bundle.getParcelable(AWW_ITEM)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.aww_item_detail_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title.setText(redditAwwItem?.title)

        (image as WebView).setBackgroundColor(Color.GREEN)
        (image as WebView).settings.useWideViewPort = true
        (image as WebView).settings.loadWithOverviewMode = true

        (image as WebView).setWebViewClient(WebViewClient())
        (image as WebView).loadUrl(redditAwwItem?.gif ?: redditAwwItem?.thumbnail)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}
