package com.askjeffreyliu.githubsearch.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.askjeffreyliu.githubsearch.MyApplication
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: MainRepository

    private val liveData = MutableLiveData<QueryResult>()

    init {
        (application as MyApplication).component.inject(this)
    }

    fun search(query: String) {
        viewModelScope.launch {
            if (TextUtils.isEmpty(query)) {
                liveData.value = null
            } else {
                repository.search(query, "stars", "desc", liveData)
            }
        }
    }

    fun getLiveData() = liveData
}
