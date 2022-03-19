package kr.co.dooribon.di

import kr.co.dooribon.BuildConfig
import kr.co.dooribon.api.calladapter.FlowCallAdapterFactory
import kr.co.dooribon.application.MainApplication.Companion.keyStorageModule
import kr.co.dooribon.config.AuthInterceptor
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
 */
class RetrofitModule {

    // 401 에러가 났을때의 exception 처리도 해줘야 함
    // JWT TOKEN이 만료가 되면 , access Token으로 refresh를 해줘서 다시 JWT Token을 발급 받아야 한다고 합니다.
    private val authInterceptor = AuthInterceptor(keyStorageModule.dooRiBonKeyStorage)

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // api 만들어주는 확장 함수
    fun <T : Any> createApi(clazz: KClass<T>): T = retrofit.create(clazz.java)

}