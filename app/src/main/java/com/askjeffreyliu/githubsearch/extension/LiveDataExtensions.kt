package com.askjeffreyliu.githubsearch.extension

import androidx.lifecycle.MutableLiveData
import com.askjeffreyliu.githubsearch.other.Event
import com.askjeffreyliu.githubsearch.other.Resource
import com.askjeffreyliu.githubsearch.other.Status


fun <T> MutableLiveData<Event<Resource<T>>>.setSuccess(data: T? = null) {
    value = Event(Resource(Status.SUCCESS, data, message = null))
}

fun <T> MutableLiveData<Event<Resource<T>>>.setLoading() {
    value = Event((Resource(Status.LOADING, value?.peekContent()?.data, message = null)))
}

fun <T> MutableLiveData<Event<Resource<T>>>.setError(message: String? = null) {
    value = Event(Resource(Status.ERROR, value?.peekContent()?.data, message))
}