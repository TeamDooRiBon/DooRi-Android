package kr.co.dooribon.api.calladapter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.dooribon.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

class FlowCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Flow<ApiResponse<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Flow<ApiResponse<R>> = flow {
        val response = call.awaitResponse()
        emit(ApiResponse.create(response))
    }.catch { error ->
        emit(ApiResponse.create(error))
    }
}