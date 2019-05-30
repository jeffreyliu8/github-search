package com.askjeffreyliu.githubsearch.endpoint


import com.askjeffreyliu.githubsearch.model.QueryResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubWebService {
    @GET("/search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("sort") sort: String?,
        @Query("order") order: String?
    ): QueryResult
}
