package com.askjeffreyliu.githubsearch.repository

import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.other.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(private val webEndpoint: WebEndpoint) {
    suspend fun search(query: String, sort: String?, order: String?): Resource<QueryResult> {
        return try {
            val response = webEndpoint.searchRepos(query, sort, order)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("Error: query result is null", null)
            } else {
                Resource.error("${response.code()} ${response.message()}", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}