package com.askjeffreyliu.githubsearch.endpoint


import com.askjeffreyliu.githubsearch.model.QueryResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebEndpoint {
    @GET("/search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("sort") sort: String?,
        @Query("order") order: String?
    ): Response<QueryResult>
}
