package com.askjeffreyliu.githubsearch.dagger

import com.askjeffreyliu.githubsearch.endpoint.WebEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
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