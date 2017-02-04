package com.brandonhogan.kotlintest.di.module


import android.content.Context
import android.content.res.Resources
import com.brandonhogan.kotlintest.AppController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(): Context = AppController.instance

    @Provides
    @Singleton
    fun provideApplication(): AppController = AppController.instance

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources = context.resources
}