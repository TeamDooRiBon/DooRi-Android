package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

// 여행 일정 추가
data class CreateScheduleReq(
    val title : String,
    val scheduleStateTime : Date,
    val scheduleEndTime : Date,
    val scheduleLocation : String,
    val scheduleMemo : String
)

data class CreateScheduleRes(
    val createScheduleRes : BaseResponse<CreateSchedule>
)

data class CreateSchedule(
    @SerializedName("_id")
    val scheduleId : String,
    @SerializedName("startTime")
    val scheduleStartTime : String,
    @SerializedName("formatTime")
    val scheduleFormatTime : String,
    @SerializedName("title")
    val scheduleTitle : String,
    @SerializedName("memo")
    val scheduleMemo : String
)

interface ScheduleAPI {
    @POST("schedule/{groupId}")
    fun createSchedule(
        @Path("groupId") groupId : String,
        @Body createScheduleReq: CreateScheduleReq
    ) : CreateScheduleRes
}