package com.askjeffreyliu.githubsearch.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.MyApplication
import com.askjeffreyliu.githubsearch.endpoint.GithubWebService
import com.askjeffreyliu.githubsearch.model.QueryResult
import javax.inject.Inject

class MainRepository(application: Application) {
    @Inject
    lateinit var webService: GithubWebService

    init {
        val app = application as MyApplication
        app.component.inject(this)
    }

    suspend fun search(query: String, sort: String?, order: String?, liveData: MutableLiveData<QueryResult>) {
        try {
            liveData.value = webService.searchRepos(query, sort, order)
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
}