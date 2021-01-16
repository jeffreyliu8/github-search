package com.askjeffreyliu.githubsearch.viewmodel

import android.text.TextUtils
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.askjeffreyliu.githubsearch.extension.setLoading
import com.askjeffreyliu.githubsearch.extension.setSuccess
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.other.Event
import com.askjeffreyliu.githubsearch.other.Resource
import com.askjeffreyliu.githubsearch.repository.MainRepository
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _liveData = MutableLiveData<Event<Resource<QueryResult>>>()
    val liveData: LiveData<Event<Resource<QueryResult>>>
        get() = _liveData

    fun search(query: String) {
        viewModelScope.launch {
            _liveData.setLoading()
            if (TextUtils.isEmpty(query)) {
                _liveData.setSuccess(null)
            } else {
                val response = repository.search(query, "stars", "desc")
                _liveData.value = Event(response)
            }
        }
    }
}