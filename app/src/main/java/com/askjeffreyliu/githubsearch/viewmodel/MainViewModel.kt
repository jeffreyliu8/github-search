package com.askjeffreyliu.githubsearch.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.repository.MainRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val carAiRepository = MainRepository(application)

    private val liveData = MutableLiveData<QueryResult>()

    fun search(query: String) {
        if (TextUtils.isEmpty(query)) {
            liveData.postValue(null)
        } else {
            carAiRepository.search(query, "stars", "desc", liveData)
        }
    }

    fun getLiveData() = liveData
}
