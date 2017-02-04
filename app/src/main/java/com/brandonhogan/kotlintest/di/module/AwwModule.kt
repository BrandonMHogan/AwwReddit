package com.brandonhogan.kotlintest.di.module

import com.brandonhogan.kotlintest.api.AwwAPI
import com.brandonhogan.kotlintest.api.AwwAPIImpl
import com.brandonhogan.kotlintest.api.RedditApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */

@Module
class AwwModule {

    @Provides
    @Singleton
    fun provideAwwAPI(redditApiService: RedditApiService): AwwAPI {
        return AwwAPIImpl(redditApiService)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApiService {
        return retrofit.create(RedditApiService::class.java)
    }

    /**
     * AwwManager is automatically provided by Dagger as we set the @Inject annotation in the
     * constructor, so we can avoid adding a 'provider method' here.
     */
}