package ai.autox.autoxzero.dependency


import com.askjeffreyliu.githubsearch.repository.MainRepository

import dagger.Component

@Component(modules = [GithubWebModule::class])
interface GithubComponent {
    fun inject(repository: MainRepository)
}