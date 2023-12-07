package com.askjeffreyliu.githubsearch.repository

import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import com.askjeffreyliu.githubsearch.model.QueryResult
import javax.inject.Inject

class MainRepository @Inject constructor(private val webEndpoint: WebEndpoint) {
    suspend fun search(query: String, sort: String?, order: String?): QueryResult? {
        val response = webEndpoint.searchRepos(query, sort, order)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}