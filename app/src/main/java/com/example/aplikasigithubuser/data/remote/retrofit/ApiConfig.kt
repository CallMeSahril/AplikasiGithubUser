import com.example.aplikasigithubuser.BuildConfig
import com.example.aplikasigithubuser.data.remote.retrofit.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL = "https://api.github.com/"


        fun getApiService(): ApiService {
            val httpClient = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {

                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(loggingInterceptor)
            } else {

                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
                httpClient.addInterceptor(loggingInterceptor)
            }


            val authInterceptor = Interceptor { chain ->
                val originalRequest = chain.request()
                val newRequest: Request = originalRequest.newBuilder()
                    .header("Authorization", "${BuildConfig.GITHUB_TOKEN}") // Add your token here
                    .build()
                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor) // Add the authentication interceptor
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
