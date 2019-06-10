package com.askjeffreyliu.githubsearch

import android.app.Application
import com.askjeffreyliu.githubsearch.dependency.DaggerWebComponent
import com.askjeffreyliu.githubsearch.dependency.WebComponent
import com.askjeffreyliu.githubsearch.dependency.WebModule


class MyApplication : Application(), ComponentProvider {

    override val component: WebComponent by lazy {
        DaggerWebComponent.builder()
            .webModule(WebModule())
            .build()
    }
}

interface ComponentProvider {
    val component: WebComponent
}