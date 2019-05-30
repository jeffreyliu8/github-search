package com.askjeffreyliu.githubsearch.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.repository.MainRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : ScopeAndroidViewModel(application) {

    private val repository = MainRepository(application)

    private val liveData = MutableLiveData<QueryResult>()

    fun search(query: String) {
        launch(context = coroutineContext) {
            if (TextUtils.isEmpty(query)) {
                liveData.value = null
            } else {
                repository.search(query, "stars", "desc", liveData)
            }
        }
    }

    fun getLiveData() = liveData
}
