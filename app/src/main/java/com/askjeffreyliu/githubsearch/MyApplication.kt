package com.askjeffreyliu.githubsearch

import android.app.Application
import com.askjeffreyliu.githubsearch.dependency.DaggerWebComponent
import com.askjeffreyliu.githubsearch.dependency.WebComponent
import com.askjeffreyliu.githubsearch.dependency.WebModule


class MyApplication : Application() {
    lateinit var component: WebComponent

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        component = DaggerWebComponent.builder()
            .webModule(WebModule())
            .build()
    }
}