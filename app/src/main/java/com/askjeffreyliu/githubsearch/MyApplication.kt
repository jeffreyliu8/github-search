package com.askjeffreyliu.githubsearch

import android.app.Application
import com.askjeffreyliu.githubsearch.dependency.DaggerWebComponent
import com.askjeffreyliu.githubsearch.dependency.WebComponent
import com.askjeffreyliu.githubsearch.dependency.WebModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger.addLogAdapter


class MyApplication : Application(), ComponentProvider {

    override val component: WebComponent by lazy {
        DaggerWebComponent.builder()
            .webModule(WebModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        addLogAdapter(AndroidLogAdapter())
    }
}

interface ComponentProvider {
    val component: WebComponent
}