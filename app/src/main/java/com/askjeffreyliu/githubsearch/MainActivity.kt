package com.askjeffreyliu.githubsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.askjeffreyliu.githubsearch.ui.component.GithubSearchUI
import com.askjeffreyliu.githubsearch.ui.component.SearchBar
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import com.askjeffreyliu.githubsearch.ui.theme.MyApplicationxxxTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            MyApplicationxxxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GithubSearchUI(
                        uiState,
                        onSearch = { viewModel.search(it.trim()) },
                        onErrorMsgShown = { viewModel.onErrorShown() },
                    )
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationxxxTheme {
            SearchBar(
                onSubmit = {
                    println(it)
                },
                defaultText = "",
                onTextChanged = {
                    println(it)
                }
            )
        }
    }
}