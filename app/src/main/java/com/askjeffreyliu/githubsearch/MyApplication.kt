package com.askjeffreyliu.githubsearch

import android.app.Application
import com.askjeffreyliu.githubsearch.dagger.DaggerAppComponent
import com.askjeffreyliu.githubsearch.dagger.AppComponent
import com.askjeffreyliu.githubsearch.dagger.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger.addLogAdapter


class MyApplication : Application(), ComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        addLogAdapter(AndroidLogAdapter())
    }
}

interface ComponentProvider {
    val component: AppComponent
}