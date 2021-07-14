package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.*

data class HomeTravelRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<HomeTravelDTO>
)

data class HomeTravelDTO(
    @SerializedName("when")
    val travelType: String,
    @SerializedName("group")
    val travelGroup: List<TravelDTO>
)

data class TravelDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("image")
    val travelThumbnailUrl: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("members")
    val travelMembers: List<String>
)

// 유저 여행 생성 Data class
data class CreateTravelReq(
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("imageIndex")
    val selectedImageIndex: Int
)

data class CreateTravelRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: InviteCodeDTO
)

data class InviteCodeDTO(
    @SerializedName("inviteCode")
    val inviteCode: String
)

// 여행 참여
data class ParticipateTravelRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ParticipateTravelDTO
)

data class ParticipateTravelDTO(
    @SerializedName("groupId")
    val travelGroupId: String,
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("host")
    val travelHost: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("image")
    val travelThumbnailImageUrl: String
)

data class TravelInfoRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: TravelInfoDTO
)

data class TravelInfoDTO(
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("members")
    val travelMembers: List<TravelMemberInfoDTO>
)

data class TravelMemberInfoDTO(
    @SerializedName("name")
    val memberName: String,
    @SerializedName("profileImage")
    val memberProfileImageUrl: String
)

// 여행 참여 , 여행에 멤버 추가
data class JoinTravelMembersRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: EditTravelMemberInfoDTO
)

data class EditTravelMemberInfoDTO(
    @SerializedName("_id")
    val travelGroupId: String,
    @SerializedName("members")
    val travelMembers: List<MemberDTO>,
    @SerializedName("schedules")
    val travelScheduleId: String,
    @SerializedName("boards")
    val travelBoardId: String,
    @SerializedName("inviteCode")
    val travelInviteCode: String,
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("startDate")
    val travelStartDate: String,
    @SerializedName("endDate")
    val travelEndDate: String,
    @SerializedName("image")
    val travelImageUrl: String
)

// 여행 편집
data class EditTravelReq(
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("startDate")
    val travelStartDate: String,
    @SerializedName("endDate")
    val travelEndDate: String,
    @SerializedName("imageIndex")
    val travelThumbnailImageIndex: Int
)

data class EditTravelRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: EditTravelDTO
)

data class EditTravelDTO(
    @SerializedName("travelName")
    val travelTitle: String,
    @SerializedName("destination")
    val travelDestination: String,
    @SerializedName("startDate")
    val travelStartDate: String,
    @SerializedName("endDate")
    val travelEndDate: String,
    @SerializedName("image")
    val travelThumbnailImageUrl: String
)

interface TravelAPI {
    // 유저 기간 별 여행 조회 / 메인 뷰 (홈뷰)
    @GET("travel")
    suspend fun fetchUserTravel(): HomeTravelRes

    // 유저 여행 생성 / 여행 생성 뷰
    @POST("travel")
    suspend fun createUserTravel(
        @Body createTravelReq: CreateTravelReq
    ): CreateTravelRes

    // 여행 참여 , 여행 정보 조회 / 참여하는 여행 정보가 맞나요? 뷰 , 각종 여행 정보 조회 시 사용
    @GET("travel/group/{inviteCode}")
    suspend fun participateExistingTravel(
        @Path("inviteCode") inviteCode: String
    ): ParticipateTravelRes

    // 여행 정보 조회 / 여행 정보 조회 시 사용
    @GET("travel/{groupId}")
    suspend fun fetchTravelInfo(
        @Path("groupId") groupId: String
    ): TravelInfoRes

    // 여행 참여 , 여행에 멤버 추가 / 참여코드 입력 후 홈으로 이동 시
    @POST("travel/{groupId}")
    suspend fun joinMemberExistingTravel(
        @Path("groupId") groupId: String,
    ): JoinTravelMembersRes

    // 여행 편집
    @PATCH("travel/{groupId}")
    suspend fun editTravel(
        @Body editTravelReq: EditTravelReq,
        @Path("groupId") groupId: String
    ): EditTravelRes
}