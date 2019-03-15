package com.askjeffreyliu.githubsearch


import ai.autox.autoxzero.dependency.DaggerGithubComponent
import ai.autox.autoxzero.dependency.GithubComponent
import ai.autox.autoxzero.dependency.GithubWebModule

import android.app.Application


class MyApplication : Application() {
    // firebase dagger componennt
    private var _component: GithubComponent? = null
    var component: GithubComponent
        get() = _component!!
        set(value) {
            _component = value
        }



    override fun onCreate() {
        super.onCreate()
        initFBComponent()
    }

    private fun initFBComponent() {
        component = DaggerGithubComponent.builder()
            .githubWebModule(GithubWebModule())
            .build()
    }
}