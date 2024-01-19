package com.askjeffreyliu.githubsearch


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.askjeffreyliu.githubsearch.ui.component.GithubSearchUI
import com.askjeffreyliu.githubsearch.ui.theme.MyApplicationxxxTheme
import com.askjeffreyliu.githubsearch.viewmodel.MainUiState
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun myTest() {
        composeTestRule.setContent {
            MyApplicationxxxTheme {
                GithubSearchUI(
                    MainUiState(),
                    onSearch = {
                        println(it)
                    },
                    onErrorMsgShown = {})
            }
        }

        composeTestRule.onNodeWithText("Search").performClick()
//
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}
