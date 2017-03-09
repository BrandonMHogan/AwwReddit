package com.brandonhogan.kotlintest.commons;

import com.brandonhogan.kotlintest.features.aww.AwwItemDetailFragment;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Brandon on 3/9/2017.
 * Description :
 */

public class GifLoadTask extends FutureTask<ByteBuffer> {

    //private static final String GIF_URL = "https://raw.githubusercontent.com/koral--/android-gif-drawable-sample/cb2d1f42b3045b2790a886d1574d3e74281de743/sample/src/main/assets/Animated-Flag-Hungary.gif";
    private final WeakReference<AwwItemDetailFragment> mFragmentReference;


    public GifLoadTask(AwwItemDetailFragment httpFragment, final String url) {
        super(new Callable<ByteBuffer>() {
            @Override
            public ByteBuffer call() throws Exception {
                URLConnection urlConnection = new URL(url).openConnection();
                urlConnection.connect();
                final int contentLength = urlConnection.getContentLength();
                if (contentLength < 0) {
                    throw new IOException("Content-Length not present");
                }
                ByteBuffer buffer = ByteBuffer.allocateDirect(contentLength);
                ReadableByteChannel channel = Channels.newChannel(urlConnection.getInputStream());
                while (buffer.remaining() > 0)
                    channel.read(buffer);
                channel.close();
                return buffer;
            }
        });
        mFragmentReference = new WeakReference<>(httpFragment);
    }

    @Override
    protected void done() {
        final AwwItemDetailFragment httpFragment = mFragmentReference.get();
        if (httpFragment == null) {
            return;
        }
        try {
            httpFragment.onGifDownloaded(get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            httpFragment.onDownloadFailed(e);
        }
    }
}