package com.askjeffreyliu.githubsearch.repository

import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.MyApplication
import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import com.askjeffreyliu.githubsearch.model.QueryResult
import javax.inject.Inject

class MainRepository {
    @Inject
    lateinit var webEndpoint: WebEndpoint

    init {
        MyApplication.instance.webComponent.inject(this)
    }

    suspend fun search(query: String, sort: String?, order: String?, liveData: MutableLiveData<QueryResult>) {
        try {
            liveData.value = webEndpoint.searchRepos(query, sort, order)
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
}