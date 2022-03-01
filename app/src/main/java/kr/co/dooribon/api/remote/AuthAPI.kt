package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kr.co.dooribon.api.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

data class AuthDTO(
    @SerializedName("user")
    val user: UserDTO,
    @SerializedName("token")
    val jwtToken: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)

data class UserDTO(
    @SerializedName("groups")
    val groups: List<String>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profileImage")
    val profileImageUrl: String,
    @SerializedName("__v")
    val version: Int
)

data class tokenReq(
    val accessToken: String,
    val refreshToken: String
)

interface AuthAPI {
    @POST("auth/user")
    suspend fun login(
        @Body tokenReq: tokenReq
    ) : BaseResponse<AuthDTO>
}