package com.askjeffreyliu.githubsearch.repository

import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import com.askjeffreyliu.githubsearch.extension.setError
import com.askjeffreyliu.githubsearch.extension.setLoading
import com.askjeffreyliu.githubsearch.extension.setSuccess
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.model.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(private val webEndpoint: WebEndpoint) {
    suspend fun search(query: String, sort: String?, order: String?, liveData: MutableLiveData<Resource<QueryResult>>) {
        liveData.setLoading()
        try {
            liveData.setSuccess(webEndpoint.searchRepos(query, sort, order))
        } catch (e: Exception) {
            liveData.setError(e.localizedMessage)
        }
    }
}