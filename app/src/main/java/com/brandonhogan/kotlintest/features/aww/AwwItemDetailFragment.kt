package com.brandonhogan.kotlintest.features.aww

import android.content.ContentResolver
import android.os.Bundle
import android.view.*
import android.widget.MediaController
import com.brandonhogan.kotlintest.MainActivity
import com.brandonhogan.kotlintest.R
import com.brandonhogan.kotlintest.commons.BaseFragment
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.commons.extensions.inflate
import com.brandonhogan.kotlintest.commons.extensions.loadGif
import com.brandonhogan.kotlintest.commons.extensions.loadImg
import javax.inject.Inject
import kotlinx.android.synthetic.main.aww_item_detail_fragment.*
import android.content.res.Resources.NotFoundException
import android.graphics.Bitmap
import java.io.IOException
import android.net.Uri
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import com.brandonhogan.kotlintest.commons.GifLoadTask
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.aww_item.view.*
import pl.droidsonroids.gif.*
import java.io.BufferedInputStream
import java.nio.ByteBuffer
import java.util.concurrent.Executors


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

    private val mExecutorService = Executors.newSingleThreadExecutor()
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
        progress.visibility = View.VISIBLE

        try {

//            if (!redditAwwItem?.gif.isNullOrEmpty()) {
//                downloadGif()
//            }
//            else {

                val requestListener = object : RequestListener<String, Bitmap> {

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        Log.e("AwwItemDetailFragment", "Error on Glide Load : { e?.message }")
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        progress.visibility = View.GONE
                        return false
                    }

                }

               imageView.loadImg(redditAwwItem?.image!!, null, requestListener)
                progress.visibility = View.GONE
            //}

        } catch (e: NotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    fun downloadGif() {
        mExecutorService.submit(GifLoadTask(this, redditAwwItem?.gif))
    }

    fun onGifDownloaded(buffer : ByteBuffer) {
        // imageView.setInputSource(InputSource.DirectByteBufferSource(buffer))

        val gifDrawable = GifDrawable(buffer)

        imageView.setImageDrawable(gifDrawable)

//        val mc = MediaController(activity)
//        mc.setMediaPlayer(imageView.drawable as GifDrawable)
//        mc.setAnchorView(imageView)
//        imageView.setOnClickListener {
//            mc.show()
//            Toast.makeText(activity, "Button 1",
//                    Toast.LENGTH_LONG).show()
//        }

        gifDrawable.start()

        progress.visibility = View.GONE
    }

    fun onDownloadFailed(exception: Exception) {
        //Snackbar.make(imageView, exception.message, Snackbar.LENGTH_SHORT)
    }
}
