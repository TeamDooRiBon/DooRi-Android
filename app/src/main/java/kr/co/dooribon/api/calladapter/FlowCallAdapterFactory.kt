package kr.co.dooribon.api.calladapter

import kotlinx.coroutines.flow.Flow
import kr.co.dooribon.api.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        require(observableType is ParameterizedType) { "resource must be parameterized" }
        val rawObservableType = getRawType(observableType)
        require(rawObservableType == ApiResponse::class.java) { "type must be a resourece" }
        val bodyType = getParameterUpperBound(0, observableType)
        return FlowCallAdapter<Any>(bodyType)
    }

    companion object {
        @JvmStatic
        fun create() = FlowCallAdapterFactory()
    }
}