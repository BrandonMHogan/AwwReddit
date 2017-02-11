package com.brandonhogan.kotlintest.di.module

import com.brandonhogan.kotlintest.api.RedditAwwDataResponse
import com.brandonhogan.kotlintest.api.deserializer.AwwDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */

@Module
class NetworkModule {

    companion object {
       @JvmStatic val URI : String = "https://www.reddit.com"
    }

    @Provides
    @Singleton
    fun provideRedditRetrofit(): Retrofit {


        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        val client: OkHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapter(RedditAwwDataResponse::class.java, AwwDeserializer())
                .create()

        return Retrofit.Builder()
                .client(client)
                .baseUrl(URI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}
