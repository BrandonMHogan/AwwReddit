package com.brandonhogan.kotlintest

import android.app.Application
import com.brandonhogan.kotlintest.di.AppModule
import com.brandonhogan.kotlintest.di.DaggerNewsComponent
import com.brandonhogan.kotlintest.di.NewsComponent

/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */
class AppController : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                //.newsModule(NewsModule()) Module with empty constructor is implicitly created by dagger.
                .build()
    }
}