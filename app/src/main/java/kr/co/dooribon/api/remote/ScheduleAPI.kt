package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.*
import java.util.*

// 여행 일정 data class
data class BaseTravelScheduleDTO(
    @SerializedName("_id")
    val travelScheduleId : String,
    @SerializedName("startTime")
    val travelScheduleStartTime : String,
    @SerializedName("formatTime")
    val travelScheduleFormatTime : String,
    @SerializedName("title")
    val travelScheduleTitle : String,
    @SerializedName("memo")
    val travelScheduleMemo : String
)

// 여행 일정 추가
data class CreateTravelScheduleReq(
    val travelScheduleTitle : String,
    val travelScheduleStateTime : Date,
    val travelScheduleEndTime : Date,
    val travelScheduleLocation : String,
    val travelScheduleMemo : String
)

data class CreateTravelScheduleRes(
    val createScheduleRes : BaseResponse<BaseTravelScheduleDTO>
)

// 여행 일정 뷰
data class TravelScheduleRes(
    val travelScheduleRes : BaseResponse<TravelScheduleDTO>
)

data class TravelScheduleDTO(
    @SerializedName("_id")
    val travelScheduleId : String,
    @SerializedName("writer")
    val travelScheduleWriter : TravelScheduleWriterDTO,
    @SerializedName("name")
    val travelScheduleName : String,
    @SerializedName("createdAt")
    val travelScheduleCreateTime : String,
    @SerializedName("title")
    val travelScheduleTitle : String,
    @SerializedName("startTime")
    val travelScheduleStartTime : String,
    @SerializedName("endTime")
    val travelScheduleEndTime : String,
    @SerializedName("location")
    val travelScheduleLocation : String,
    @SerializedName("memo")
    val travelScheduleMemo : String
)

data class TravelScheduleWriterDTO(
    @SerializedName("name")
    val name : String,
    @SerializedName("profileImage")
    val profileImageUrl : String
)

// 여행 일정 편집
data class EditTravelScheduleReq(
    @SerializedName("title")
    val travelScheduleTitle : String,
    @SerializedName("startTime")
    val travelScheduleStartTime : Date,
    @SerializedName("endTime")
    val travelScheduleEndTime : Date,
    @SerializedName("location")
    val travelScheduleLocation : String,
    @SerializedName("memo")
    val travelScheduleMemo : String
)

data class EditTravelScheduleRes(
    val editTravelRes : BaseResponse<BaseTravelScheduleDTO>
)

// 여행 일정 삭제
data class DeleteTravelScheduleRes(
    val deleteTravelScheduleRes : BaseResponse<BaseTravelScheduleDTO>
)

// 특정 날짜 일정 전체 조회
data class CertainTravelScheduleDTO(
    @SerializedName("day")
    val travelScheduleDay : Int,
    @SerializedName("date")
    val travelScheduleDate : String,
    @SerializedName("schedules")
    val travelSchedule : List<BaseTravelScheduleDTO>
)

data class CertainTravelScheduleRes(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : CertainTravelScheduleDTO
)

interface ScheduleAPI {
    @POST("schedule/{groupId}")
    fun createTravelSchedule(
        @Path("groupId") groupId : String,
        @Body createScheduleReq: CreateTravelScheduleReq
    ) : CreateTravelScheduleRes

    @GET("schedule/{groupId}/{scheduleId}")
    fun fetchTravelSchedule(
        @Path("groupId") groupId : String,
        @Path("scheduleId") scheduleId : String
    ) : TravelScheduleRes

    @PATCH("schedule/{groupId}/{scheduleId}")
    fun editTravelSchedule(
        @Path("groupId") groupId : String,
        @Path("scheduleId") scheduleId : String
    ) : EditTravelScheduleRes

    @DELETE("schedule/{groupId}/{scheduleId}")
    fun deleteTravelSchedule(
        @Path("groupId") groupId : String,
        @Path("scheduleId") scheduleId : String
    ) : DeleteTravelScheduleRes

    @GET("schedule/daily/{groupId}/{date}")
    fun fetchCertainTravelSchedule(
        @Path("groupId") groupId : String,
        @Path("date") date : String
    ) : CertainTravelScheduleRes
}