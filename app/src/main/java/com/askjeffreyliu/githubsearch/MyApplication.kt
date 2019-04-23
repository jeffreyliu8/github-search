package com.askjeffreyliu.githubsearch


import ai.autox.autoxzero.dependency.DaggerGithubComponent
import ai.autox.autoxzero.dependency.GithubComponent
import ai.autox.autoxzero.dependency.GithubWebModule

import android.app.Application


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