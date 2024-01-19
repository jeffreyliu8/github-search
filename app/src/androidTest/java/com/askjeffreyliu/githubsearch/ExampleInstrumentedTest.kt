package com.askjeffreyliu.githubsearch


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import com.askjeffreyliu.githubsearch.other.Status
import com.askjeffreyliu.githubsearch.repository.MainRepository
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<MyApplication>()
        assertEquals("com.askjeffreyliu.githubsearch", appContext.packageName)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() = runBlocking {
        val httpClient = OkHttpClient.Builder()

        val endpoint = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(WebEndpoint::class.java)

        val repository = MainRepository(endpoint)

        // Given a fresh ViewModel
        val viewModel = MainViewModel(repository)


        // When adding a new task

        viewModel.search("square")
        delay(12000)
        assertNotNull(viewModel.uiState.value.queryResult)
        assertNotEquals(0, viewModel.uiState.value.queryResult?.totalCount)
    }
}
