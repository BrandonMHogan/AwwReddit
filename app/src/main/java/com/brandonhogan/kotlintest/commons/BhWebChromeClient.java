package com.brandonhogan.kotlintest.commons;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Brandon on 2/12/2017.
 * Description :
 */

public class BhWebChromeClient extends WebChromeClient {
    private ProgressListener mListener;

    public BhWebChromeClient(ProgressListener listener) {
        mListener = listener;
    }

    public BhWebChromeClient() {
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
      //  mListener.onUpdateProgress(newProgress);
        super.onProgressChanged(view, newProgress);
    }

    public interface ProgressListener {
        void onUpdateProgress(int progressValue);
    }
}