package com.brandonhogan.kotlintest.features.aww

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.brandonhogan.kotlintest.MainActivity
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.BaseFragment
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.commons.extensions.inflate
import com.bumptech.glide.Glide
import javax.inject.Inject
import kotlinx.android.synthetic.main.aww_item_detail_fragment.*
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.io.InputStream
import java.net.URI
import java.util.concurrent.TimeUnit


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

        (activity as MainActivity).setActionBarTitle(redditAwwItem?.title ?: "")

        if (redditAwwItem?.gif == null) {
            Glide.with(context).load(redditAwwItem?.thumbnail).asBitmap().atMost().fitCenter().placeholder(R.drawable.progress_anim).into((image as ImageView))
        }
        else {
            Glide.with(context).load(redditAwwItem?.gif as String).asGif().fitCenter().placeholder(R.drawable.progress_anim).into((image as ImageView))
        }


//        (image as WebView).setBackgroundColor(Color.GREEN)
//        (image as WebView).settings.useWideViewPort = true
//        (image as WebView).settings.loadWithOverviewMode = true
//
//        (image as WebView).setWebViewClient(WebViewClient())
//        (image as WebView).loadUrl(redditAwwItem?.gif ?: redditAwwItem?.thumbnail)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}
