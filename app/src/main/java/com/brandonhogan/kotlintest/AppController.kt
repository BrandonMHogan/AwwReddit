package com.brandonhogan.kotlintest

import android.app.Application
import com.brandonhogan.kotlintest.di.component.AppComponent
import com.brandonhogan.kotlintest.di.component.DaggerAppComponent

/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */
class AppController : Application() {

    companion object {
        @JvmStatic lateinit var instance: AppController
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()

//        appComponent = DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                //.newsModule(AwwModule()) Module with empty constructor is implicitly created by dagger.
//                .build()

    }
}