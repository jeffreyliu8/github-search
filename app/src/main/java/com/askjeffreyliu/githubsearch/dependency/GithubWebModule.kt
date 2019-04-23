package ai.autox.autoxzero.dependency


import com.askjeffreyliu.githubsearch.BuildConfig
import com.askjeffreyliu.githubsearch.endpoint.GithubWebService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GithubWebModule {
    @Provides
    fun provideWebService(): GithubWebService {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            // add your other interceptors …
            // add logging as last interceptor
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)  // <-- this is the important line!
        }

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(GithubWebService::class.java)
    }
}