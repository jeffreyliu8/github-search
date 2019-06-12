package com.askjeffreyliu.githubsearch.extension

import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.model.Resource
import com.askjeffreyliu.githubsearch.model.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T? = null) =
    postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(ResourceState.LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(ResourceState.ERROR, value?.data, message))