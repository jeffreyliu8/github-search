package com.askjeffreyliu.githubsearch.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.askjeffreyliu.githubsearch.MyApplication
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: MainRepository

    private val liveData = MutableLiveData<QueryResult>()

    init {
        MyApplication.instance.webComponent.inject(this)
    }

    fun search(query: String) {
        viewModelScope.launch  {
            if (TextUtils.isEmpty(query)) {
                liveData.value = null
            } else {
                repository.search(query, "stars", "desc", liveData)
            }
        }
    }

    fun getLiveData() = liveData
}
