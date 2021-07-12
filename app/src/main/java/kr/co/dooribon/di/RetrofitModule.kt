package kr.co.dooribon.di

import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * Retrofit 객체 생성해주는 Module
 */
class RetrofitModule {

    // 되는지 확인해봐야해
    private val authTokenInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().header(HEADER_TOKEN,"TOKEN_IN_HERE")
        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }

    private fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(authTokenInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
            .build()

    private fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()

    // api 만들어주는 확장 함수
    fun<T : Any> createApi(clazz : KClass<T>) : T = provideRetrofit().create(clazz.java)

    companion object {
        // example baseCode , 서버 나오면 바꿔야 됨
        private const val BASE_URL = "http://13.209.82.176:5000/"
        private const val MEDIA_TYPE = "application/json"
        private const val HEADER_TOKEN = "x-access-token"
    }
}