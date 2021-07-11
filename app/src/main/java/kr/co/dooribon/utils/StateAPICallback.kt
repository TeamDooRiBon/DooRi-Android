package kr.co.dooribon.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kr.co.dooribon.view.calendarpicker.entity.CalendarEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * 서버통신의 상태에 따라 구분해서 처리해줄 수 있는 코드
 */
class StateAPICallback<T>(private val callback : (APIState<T>) -> Unit) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        when {
            response.isSuccessful && response.code() == HttpURLConnection.HTTP_NO_CONTENT -> {
                callback.invoke(APIState.NoContents())
            }
            response.isSuccessful -> {
                response.body()?.let {
                    callback.invoke(APIState.Success(it))
                } ?: callback.invoke(APIState.Fail())
            }
            else -> callback.invoke(APIState.Fail())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        callback.invoke(APIState.Fail())
    }
}

/**
 * Retry를 해줄 수 있게 flow를 써야할 지도 모르니까...?
 */
@ExperimentalCoroutinesApi
fun <T> Call<T>.asCallbackFlow() = callbackFlow<T> {
    enqueue(object : Callback<T>{
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if(response.isSuccessful) {
                response.body()?.let { offer(it) } ?: close()
            } else {
                close()
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            close(t)
        }
    })
    awaitClose()
}

sealed class APIState<out T>{
    class Success<out T>(val body : T) : APIState<T>()
    class NoContents<out T> : APIState<T>()
    class Fail<out T> : APIState<T>()
}