package com.brandonhogan.kotlintest.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
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
        return Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}