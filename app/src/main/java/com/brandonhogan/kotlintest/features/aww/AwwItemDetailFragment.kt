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
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
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

        if (redditAwwItem?.gif.isNullOrEmpty()) {
            Glide.with(context).load(redditAwwItem?.image).asBitmap().fitCenter().placeholder(R.drawable.progress_anim).into((image as ImageView))
        }
        else {
//           // var imgViewTarget : GlideDrawableImageViewTarget = GlideDrawableImageViewTarget(image as ImageView)
//          //  Glide.with(context).load(redditAwwItem?.gif).placeholder(R.drawable.progress_anim).into(imgViewTarget)
//
//
//           Glide.with(context).load(redditAwwItem?.gif).placeholder(R.drawable.progress_anim).into((image))
//        }


            (web as WebView).setBackgroundColor(Color.TRANSPARENT)
            (web as WebView).settings.useWideViewPort = true
            (web as WebView).settings.loadWithOverviewMode = true

            (web as WebView).setWebViewClient(WebViewClient())
            (web as WebView).loadUrl(redditAwwItem?.gif)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}
