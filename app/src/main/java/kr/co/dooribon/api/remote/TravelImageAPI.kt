package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

// 여행 등록할 때 보여질 16개 이미지
data class DefaultTravelImageDTO(
    val defaultTravelImageRes: BaseResponse<Map<String, String>>
)

// 홈뷰에서 큰 이미지 뷰 채워넣을 이미지
data class HomeTravelImageDTO(
    @SerializedName("image")
    val homeImageUrl: String
)

data class HomeTravelImageRes(
    val homeTravelImageRes: BaseResponse<HomeTravelImageDTO>
)

interface TravelImageAPI {
    // 여행 썸네일 이미지 16개 가져오는 api
    @GET("image")
    fun fetchDefaultTravelImage(): DefaultTravelImageDTO

    // 홈 화면에서 이미지 큰거 가져오는 api
    @GET("image/{groupId}")
    fun fetchHomeTravelImage(): HomeTravelImageRes
}