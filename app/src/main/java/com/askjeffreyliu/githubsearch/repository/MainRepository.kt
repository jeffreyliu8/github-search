package com.askjeffreyliu.githubsearch.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.MyApplication
import com.askjeffreyliu.githubsearch.endpoint.GithubWebService
import com.askjeffreyliu.githubsearch.model.QueryResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository(application: Application) {
    @Inject
    lateinit var webService: GithubWebService

    init {
        val app = application as MyApplication
        app.component.inject(this)
    }

    fun search(query: String, sort: String?, order: String?, liveData: MutableLiveData<QueryResult>) {
        webService.searchRepos(query, sort, order).enqueue(object : Callback<QueryResult> {
            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                println("${t.localizedMessage}")
            }

            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                } else {
                    println("Error code: " + response.code())
                }
            }
        })
    }
}