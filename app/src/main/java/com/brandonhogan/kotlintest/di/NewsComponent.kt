package com.brandonhogan.kotlintest.di

import com.brandonhogan.kotlintest.di.news.NewsModule
import com.brandonhogan.kotlintest.features.news.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Brandon on 2/3/2017.
 * Description :
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}