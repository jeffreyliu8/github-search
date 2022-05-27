package com.askjeffreyliu.githubsearch


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.other.Resource
import com.askjeffreyliu.githubsearch.ui.component.GithubSearchUI
import com.askjeffreyliu.githubsearch.ui.theme.MyApplicationxxxTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun myTest() {
        val input = Resource.success(
            QueryResult(
                totalCount = 0,
                incompleteResults = false,
                items = emptyList()
            )
        )

        composeTestRule.setContent {
            MyApplicationxxxTheme {
                GithubSearchUI(
                    input,
                    onSearch = {
                        println(it)
                    })
            }
        }

        composeTestRule.onNodeWithText("Search1").performClick()
//
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}
