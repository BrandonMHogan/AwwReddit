package com.brandonhogan.kotlintest.commons;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Brandon on 2/9/2017.
 * Description :
 */

//public class MyGlideModule implements GlideModule {
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//        // Do nothing.
//    }
//
//    @Override
//    public void registerComponents(Context context, Glide glide) {
//        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//        // set your timeout here
//        builder.readTimeout(60, TimeUnit.SECONDS);
//        builder.writeTimeout(60, TimeUnit.SECONDS);
//        builder.connectTimeout(60, TimeUnit.SECONDS);
//
//        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(builder.build()));
//    }
//}
