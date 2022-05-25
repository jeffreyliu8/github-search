package com.askjeffreyliu.githubsearch


import androidx.compose.ui.test.junit4.createComposeRule
import com.askjeffreyliu.githubsearch.ui.component.SearchBar
import com.askjeffreyliu.githubsearch.ui.theme.MyApplicationxxxTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            MyApplicationxxxTheme {
                SearchBar(onSelected = {

                })
            }
        }

//        composeTestRule.onNodeWithText("Continue").performClick()
//
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}
