package com.brandonhogan.kotlintest.di.component

import com.brandonhogan.kotlintest.di.module.AppModule
import com.brandonhogan.kotlintest.di.module.NetworkModule
import com.brandonhogan.kotlintest.di.module.AwwModule
import com.brandonhogan.kotlintest.features.aww.AwwListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        AwwModule::class,
        NetworkModule::class)
)
interface AppComponent {
    fun inject(awwListFragment: AwwListFragment)
}