package com.askjeffreyliu.githubsearch.dependency


import com.askjeffreyliu.githubsearch.repository.MainRepository

import dagger.Component

@Component(modules = [WebModule::class])
interface WebComponent {
    fun inject(repository: MainRepository)
}