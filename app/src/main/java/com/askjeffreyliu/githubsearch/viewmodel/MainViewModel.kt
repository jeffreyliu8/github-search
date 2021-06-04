package com.askjeffreyliu.githubsearch.viewmodel

import android.text.TextUtils
import androidx.lifecycle.*
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.other.Resource
import com.askjeffreyliu.githubsearch.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _queryResultFlow = MutableStateFlow(
        Resource.success(
            QueryResult(
                totalCount = 0,
                incompleteResults = false,
                items = emptyList()
            )
        )
    )
    val queryResultFlow: StateFlow<Resource<QueryResult>> = _queryResultFlow

    fun search(query: String) {
        viewModelScope.launch(IO) {
            _queryResultFlow.value = Resource.loading(null)
            if (TextUtils.isEmpty(query)) {
                _queryResultFlow.value = Resource.success(null)
            } else {
                val response = repository.search(query, "stars", "desc")
                _queryResultFlow.value = response
            }
        }
    }
}