package com.askjeffreyliu.githubsearch.endpoint


import com.askjeffreyliu.githubsearch.model.QueryResult
import retrofit2.Call
import retrofit2.http.*

interface GithubWebService {
    @GET("/search/repositories")
    fun searchRepos(
        @Query("q") query: String,
        @Query("sort") sort: String?,
        @Query("order") order: String?
    ): Call<QueryResult>
}
