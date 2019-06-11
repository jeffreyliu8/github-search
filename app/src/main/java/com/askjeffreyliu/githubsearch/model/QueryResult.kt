package com.askjeffreyliu.githubsearch.model

import com.google.gson.annotations.SerializedName


data class QueryResult(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incompleteResults")
    val incompleteResults: Boolean,
    val items: List<QueryItem>? = null
)