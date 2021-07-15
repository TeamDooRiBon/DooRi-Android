package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// 여행 등록할 때 보여질 16개 이미지
data class DefaultTravelImageDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<String>
)

// 홈뷰에서 큰 이미지 뷰 채워넣을 이미지
data class HomeTravelImageDTO(
    @SerializedName("image")
    val homeImageUrl: String
)

data class HomeTravelImageRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: HomeTravelImageDTO
)

interface TravelImageAPI {
    // 여행 썸네일 이미지 16개 가져오는 api
    // TODO : 시간이 문제인거지 , API자체는 문제아님 해결
    @GET("image")
    fun fetchDefaultTravelImage(): Call<DefaultTravelImageDTO>

    // 홈 화면에서 이미지 큰거 가져오는 api
    // 해결
    @GET("image/{groupId}")
    suspend fun fetchHomeTravelImage(
        @Path("groupId") groupId: String
    ): HomeTravelImageRes
}