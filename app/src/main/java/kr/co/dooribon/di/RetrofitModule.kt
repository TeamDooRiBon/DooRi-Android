package kr.co.dooribon.di

import kr.co.dooribon.BuildConfig
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
 *
 * jwt Token만 있으면 됩니다.
 *
 * TODO : Lazy로 캐시를 좀 해놔야지 훈기야 왜 하나만 생각하고 둘은 생각을 못하니;;;
 */
class RetrofitModule {

    // 401 에러가 났을때의 exception 처리도 해줘야 함
    // JWT TOKEN이 만료가 되면 , accessToken으로 refresh를 해줘서 다시 JWT Token을 발급 받아야 한다고 합니다.
    private val authTokenInterceptor by lazy {
        Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder().header(
                HEADER_TOKEN, BuildConfig.JWT_TOKEN
            ).addHeader("Content-Type", MEDIA_TYPE).addHeader("Accept", MEDIA_TYPE)
            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(authTokenInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // api 만들어주는 확장 함수
    fun <T : Any> createApi(clazz: KClass<T>): T = retrofit.create(clazz.java)

    companion object {
        private const val MEDIA_TYPE = "application/json"
        private const val HEADER_TOKEN = "x-auth-token"
    }
}