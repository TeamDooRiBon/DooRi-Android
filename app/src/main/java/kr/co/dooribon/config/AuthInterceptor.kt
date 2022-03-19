package kr.co.dooribon.config

import android.util.Log
import kr.co.dooribon.api.local.DooRiBonKeyStorage
import kr.co.dooribon.utils.debugE
import kr.co.dooribon.utils.debugSSong
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class AuthInterceptor(
    private val keyStorage: DooRiBonKeyStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val baseRequest = chain.request()
        val builder = baseRequest.newBuilder().addHeader(
            HEADER_TOKEN, keyStorage.jwtToken
        ).addHeader("Content-Type", MEDIA_TYPE).addHeader("Accept", MEDIA_TYPE)
        val request = builder.build()
        val newRequest =
            builder.addHeader("Authorization", "Bearer ${keyStorage.accessToken}").build()
        try {
            var response = chain.proceed(request) // baseRequest를 실행해본다.
            if (response.code == 401) {
                kotlin.runCatching {
                    response.body?.close() // 만약 401 즉, authorization error가 발생할 경우엔 기존 response를 닫고
                    chain.proceed(newRequest)
                }.onSuccess {
                    response = it
                }.onFailure {
                    debugE("토큰 갱신 실패")
                }
            }

            return response
        } catch (exception: Exception) {
            return Response.Builder().code(500).protocol(Protocol.HTTP_1_1)
                .message("Internal Server Error_SSong-develop")
                .request(newRequest).body("$exception".toResponseBody(null)).build()
        }
    }

    companion object {
        private const val MEDIA_TYPE = "application/json"
        private const val HEADER_TOKEN = "x-auth-token"
    }
}