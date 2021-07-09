package kr.co.dooribon.di

import android.util.Log
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit 객체 생성해주는 Module
 */
class RetrofitModule {
    private fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                try {
                    var response = chain.proceed(request)
                    if (response.code == 408) { // timeout인 경우
                        kotlin.runCatching {
                            response.body?.close()
                            chain.proceed(request)
                        }.onSuccess {
                            response = it
                        }.onFailure {
                            Log.d("error", "timeOut Error")
                        }
                    }
                    response
                } catch (e: Throwable) {
                    throw e
                }
            })
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

    companion object {
        // example baseCode , 서버 나오면 바꿔야 됨
        private const val BASE_URL = "https://github.com"

        // Retrofit 객체가 필요할 경우 가져오게하는 함수수
        fun getRetrofitInstance() = RetrofitModule().provideRetrofit()
    }
}