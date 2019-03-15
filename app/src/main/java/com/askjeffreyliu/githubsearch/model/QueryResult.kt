package com.askjeffreyliu.githubsearch.model


data class QueryResult(
    val total_count: Int ,
    val incomplete_results: Boolean,
    val items: List<QueryItem>? = null
)