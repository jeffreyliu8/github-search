package com.askjeffreyliu.githubsearch.dagger


import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(viewModel: MainViewModel)
}