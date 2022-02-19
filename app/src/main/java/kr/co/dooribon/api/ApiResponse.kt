package kr.co.dooribon.api

import retrofit2.Response

@Suppress("unused")
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error", 0)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val headers = response.headers()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body,
                        header = headers
                    )
                }
            } else {
                val message = response.errorBody()?.string()
                val errorMessage = if (message.isNullOrEmpty()) {
                    response.message()
                } else {
                    message
                }
                ApiErrorResponse(errorMessage ?: "unknown error", response.code())
            }
        }

    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T,
    val header: okhttp3.Headers
) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String, val statusCode: Int) : ApiResponse<T>()
