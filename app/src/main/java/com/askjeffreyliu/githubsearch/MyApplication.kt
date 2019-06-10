package com.askjeffreyliu.githubsearch

import android.app.Application
import com.askjeffreyliu.githubsearch.dependency.DaggerWebComponent
import com.askjeffreyliu.githubsearch.dependency.WebComponent
import com.askjeffreyliu.githubsearch.dependency.WebModule


class MyApplication : Application() {
    lateinit var webComponent: WebComponent

    companion object {
        lateinit var instance: MyApplication private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        webComponent = DaggerWebComponent.builder()
            .webModule(WebModule())
            .build()
    }
}