package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*
import java.util.*

// 여행 일정 data class
data class BaseTravelScheduleDTO(
    @SerializedName("_id")
    val travelScheduleId: String,
    @SerializedName("startTime")
    val travelScheduleStartTime: String,
    @SerializedName("formatTime")
    val travelScheduleFormatTime: String,
    @SerializedName("title")
    val travelScheduleTitle: String,
    @SerializedName("memo")
    val travelScheduleMemo: String
)

// 여행 일정 추가
data class CreateTravelScheduleReq(
    @SerializedName("title")
    val travelScheduleTitle: String,
    @SerializedName("startTime")
    val travelScheduleStateTime: String,
    @SerializedName("endTime")
    val travelScheduleEndTime: String,
    @SerializedName("location")
    val travelScheduleLocation: String,
    @SerializedName("memo")
    val travelScheduleMemo: String
)

data class CreateTravelScheduleRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<BaseTravelScheduleDTO>
)

// 여행 일정 뷰
data class TravelScheduleRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: TravelScheduleDTO
)

data class TravelScheduleDTO(
    @SerializedName("_id")
    val travelScheduleId: String,
    @SerializedName("writer")
    val travelScheduleWriter: TravelScheduleWriterDTO,
    @SerializedName("name")
    val travelScheduleName: String,
    @SerializedName("createdAt")
    val travelScheduleCreateTime: String,
    @SerializedName("title")
    val travelScheduleTitle: String,
    @SerializedName("startTime")
    val travelScheduleStartTime: String,
    @SerializedName("endTime")
    val travelScheduleEndTime: String,
    @SerializedName("location")
    val travelScheduleLocation: String,
    @SerializedName("memo")
    val travelScheduleMemo: String
)

data class TravelScheduleWriterDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("profileImage")
    val profileImageUrl: String
)

// 여행 일정 편집
data class EditTravelScheduleReq(
    @SerializedName("title")
    val travelScheduleTitle: String,
    @SerializedName("startTime")
    val travelScheduleStartTime: String,
    @SerializedName("endTime")
    val travelScheduleEndTime: String,
    @SerializedName("location")
    val travelScheduleLocation: String,
    @SerializedName("memo")
    val travelScheduleMemo: String
)

data class EditTravelScheduleRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<BaseTravelScheduleDTO>
)

// 여행 일정 삭제
data class DeleteTravelScheduleRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<BaseTravelScheduleDTO>
)

// 특정 날짜 일정 전체 조회
data class CertainTravelScheduleDTO(
    @SerializedName("day")
    val travelScheduleDay: Int,
    @SerializedName("date")
    val travelScheduleDate: String,
    @SerializedName("schedules")
    val travelSchedule: List<BaseTravelScheduleDTO>
)

data class CertainTravelScheduleRes(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: CertainTravelScheduleDTO
)

interface ScheduleAPI {
    // 해결
    @POST("schedule/{groupId}")
    fun createTravelSchedule(
        @Path("groupId") groupId: String,
        @Body createScheduleReq: CreateTravelScheduleReq
    ): Call<CreateTravelScheduleRes>

    // 해결
    @GET("schedule/{groupId}/{scheduleId}")
    fun fetchTravelSchedule(
        @Path("groupId") groupId: String,
        @Path("scheduleId") scheduleId: String
    ): Call<TravelScheduleRes>

    // 해결
    @PATCH("schedule/{groupId}/{scheduleId}")
    fun editTravelSchedule(
        @Path("groupId") groupId: String,
        @Path("scheduleId") scheduleId: String,
        @Body editTravelScheduleReq: EditTravelScheduleReq
    ): Call<EditTravelScheduleRes>

    // 해결
    @DELETE("schedule/{groupId}/{scheduleId}")
    fun deleteTravelSchedule(
        @Path("groupId") groupId: String,
        @Path("scheduleId") scheduleId: String
    ): Call<DeleteTravelScheduleRes>

    // 해결
    @GET("schedule/daily/{groupId}/{date}")
    fun fetchCertainTravelSchedule(
        @Path("groupId") groupId: String,
        @Path("date") date: String
    ): Call<CertainTravelScheduleRes>
}