package com.brandonhogan.kotlintest.commons;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by Brandon on 2/9/2017.
 * Description :
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Do nothing.
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        OkHttpClient client = new OkHttpClient();
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
        glide.register(GlideUrl.class, InputStream.class, factory);
    }
}
