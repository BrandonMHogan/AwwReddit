package com.brandonhogan.kotlintest.di.module

import com.brandonhogan.kotlintest.api.AwwAPI
import com.brandonhogan.kotlintest.api.AwwAPIImpl
import com.brandonhogan.kotlintest.api.AwwApiService
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
    fun provideAwwAPI(awwApiService: AwwApiService): AwwAPI {
        return AwwAPIImpl(awwApiService)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): AwwApiService {
        return retrofit.create(AwwApiService::class.java)
    }

    /**
     * AwwManager is automatically provided by Dagger as we set the @Inject annotation in the
     * constructor, so we can avoid adding a 'provider method' here.
     */
}