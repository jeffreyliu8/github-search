package com.askjeffreyliu.githubsearch.model

import com.google.gson.annotations.SerializedName

data class QueryItem(
    @SerializedName("full_name")
    val fullName: String,
    val description: String? = null,
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazersCount: Int
)