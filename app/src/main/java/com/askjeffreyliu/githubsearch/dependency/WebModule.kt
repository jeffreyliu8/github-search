package com.askjeffreyliu.githubsearch.dependency


import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebModule {
    @Provides
    fun provideWebService(): WebEndpoint {
        val httpClient = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
//            val logging = HttpLoggingInterceptor()
//            // set your desired log level
//            // add your other interceptors …
//            // add logging as last interceptor
//            logging.level = HttpLoggingInterceptor.Level.BODY
//            httpClient.addInterceptor(logging)  // <-- this is the important line!
//        }

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(WebEndpoint::class.java)
    }
}