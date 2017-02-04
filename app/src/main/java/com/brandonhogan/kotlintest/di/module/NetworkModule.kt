package com.brandonhogan.kotlintest.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRedditRetrofit(): Retrofit {


        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        val client: OkHttpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}
