package kr.co.dooribon.api.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * questions에서 질문을 만약 1번을 선택하면 questionsWeight의 1번배열의 값을 전부 더해준다. 이게 결과값이 된다.
 */
// 성향 테스트 질문 조회
data class TravelTendencyQuestionRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ParentQuestionDTO>
)

data class ParentQuestionDTO(
    @SerializedName("title")
    val parentQuestionTitle: String,
    @SerializedName("content")
    val childQuestions: List<ChildQuestionDTO>
)

data class ChildQuestionDTO(
    @SerializedName("answer")
    val questionsTitle: String,
    @SerializedName("weight")
    val questionsWeight: List<Int>
)

// 성향 테스트 카운팅 조회
data class TravelTendencyQuestionCountRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ParentQuestionCountDTO>
)
data class ParentQuestionCountDTO(
    @SerializedName("title")
    val parentQuestionTitle: String,
    @SerializedName("content")
    val childQuestions: List<ChildQuestionCountDTO>
)

data class ChildQuestionCountDTO(
    @SerializedName("answer")
    val question: String,
    @SerializedName("count")
    val questionAnswerCount: Int
)

// 그룹 성향 테스트 결과 전체 조회
data class GroupTravelTendencyRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: GroupTravelTendencyResultDTO
)

data class GroupTravelTendencyResultDTO(
    @SerializedName("myResult")
    val myTravelTendencyResult : GroupTravelTendencyDTO,
    @SerializedName("othersResult")
    val otherTravelTendencyResult : List<GroupTravelTendencyDTO>
)

data class GroupTravelTendencyDTO(
    @SerializedName("tag")
    val tendencyTag: List<String>,
    @SerializedName("_id")
    val tendencyId: String,
    @SerializedName("member")
    val tendencyUserMember: MemberDTO,
    @SerializedName("title")
    val tendencyTitle: String,
    @SerializedName("aOSResultImage")
    val tendencyResultImageUrl: String,
    @SerializedName("thumbnail")
    val tendencyThumbnailImageUrl: String
)

data class MemberDTO(
    @SerializedName("_id")
    val memberId: String,
    @SerializedName("name")
    val memberName: String,
    @SerializedName("profileImage")
    val memberProfileImageUrl: String
)

// 성향테스트 결과 저장
data class StoreTravelTendencyReq(
    @SerializedName("score")
    val tendencyScore: List<Int>,
    @SerializedName("choice")
    val tendencyChoice: List<Int>
)

@Parcelize
data class StoreTravelTendencyDTO(
    @SerializedName("member")
    val memberName: String,
    @SerializedName("title")
    val tendencyTitle: String,
    @SerializedName("tag")
    val tendencyTag: List<String>,
    @SerializedName("aOSResultImage")
    val tendencyResultImageUrl: String,
    @SerializedName("thumbnail")
    val tendencyThumbnailImageUrl: String
) : Parcelable

data class StoreTravelTendencyRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: StoreTravelTendencyDTO
)

interface TendencyAPI {
    // 성향 테스트 질문 조회 , 이건 함
    // 해결
    @GET("tendency/question")
    suspend fun fetchTravelTendencyQuestion(): TravelTendencyQuestionRes

    // 성향 테스트 카운팅 조회
    // 해결
    @GET("tendency/question/{groupId}")
    suspend fun fetchTravelTendencyQuestionCount(
        @Path("groupId") groupId: String
    ): TravelTendencyQuestionCountRes

    // 그룹 성향테스트 결과 전체 조회
    // 해결
    @GET("tendency/{groupId}")
    suspend fun fetchGroupTravelTendency(
        @Path("groupId") groupId: String
    ): GroupTravelTendencyRes

    // 성향테스트 결과 저장
    // 해결
    @POST("tendency/{groupId}")
    suspend fun storeTravelTendency(
        @Body storeTravelTendencyReq: StoreTravelTendencyReq,
        @Path("groupId") groupId: String
    ): StoreTravelTendencyRes
}