package com.askjeffreyliu.githubsearch.dependency


import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class])
interface WebComponent {
    fun inject(viewModel: MainViewModel)
}