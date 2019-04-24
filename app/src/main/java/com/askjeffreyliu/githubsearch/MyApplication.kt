package com.askjeffreyliu.githubsearch

import android.app.Application
import com.askjeffreyliu.githubsearch.dependency.DaggerGithubComponent
import com.askjeffreyliu.githubsearch.dependency.GithubComponent
import com.askjeffreyliu.githubsearch.dependency.GithubWebModule


class MyApplication : Application() {
    lateinit var component: GithubComponent

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        component = DaggerGithubComponent.builder()
            .githubWebModule(GithubWebModule())
            .build()
    }
}