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
            MyApplicationxxxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GithubSearchUI(viewModel, onSearch = {
                        viewModel.search(it.trim())
                    })
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationxxxTheme {
            SearchBar(onSelected = {
                println(it)
            })
        }
    }
}