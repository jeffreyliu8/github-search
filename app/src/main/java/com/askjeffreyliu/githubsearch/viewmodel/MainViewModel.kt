package com.askjeffreyliu.githubsearch.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    fun search(query: String) {
        if (query.isEmpty()) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    queryResult = null,
                )
            }
            return
        }
        viewModelScope.launch(IO) {
            _uiState.update {
                it.copy(
                    isLoading = true,
                )
            }
            try {
                val response = repository.search(query, "stars", "desc")
                _uiState.update {
                    it.copy(
                        queryResult = response,
                    )
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e("tag", "some exception $e")
                Log.e("tag", e.stackTraceToString())
                _uiState.update {
                    it.copy(
                        errorMsg = e.message,
                    )
                }
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun onErrorShown() {
        _uiState.update {
            it.copy(
                errorMsg = null,
            )
        }
    }
}

data class MainUiState(
    val isLoading: Boolean = false,
    val queryResult: QueryResult? = null,
    val errorMsg: String? = null,
)