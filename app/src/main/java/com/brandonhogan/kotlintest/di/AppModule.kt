package com.brandonhogan.kotlintest.di


import android.content.Context
import com.brandonhogan.kotlintest.AppController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: AppController) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideApplication(): AppController {
        return app
    }
}