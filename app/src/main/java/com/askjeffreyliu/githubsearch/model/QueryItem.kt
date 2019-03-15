package com.askjeffreyliu.githubsearch.model

data class QueryItem(
    val full_name: String,
    val description: String? = null,
    val owner: Owner,
    val stargazers_count: Int
)