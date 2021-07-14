package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * questions에서 질문을 만약 1번을 선택하면 questionsWeight의 1번배열의 값을 전부 더해준다. 이게 결과값이 된다.
 */
// 성향 테스트 질문 조회
data class ChildQuestionDTO(
    @SerializedName("title")
    val questions: List<String>,
    @SerializedName("weight")
    val questionsWeight: List<List<Int>>
)

data class ParentQuestionDTO(
    @SerializedName("title")
    val parentQuestionTitle: String,
    @SerializedName("content")
    val childQuestions: ChildQuestionDTO
)

data class QuestionListDTO(
    @SerializedName("question")
    val questionList: List<ParentQuestionDTO>
)

data class TravelTendencyQuestionRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: QuestionListDTO
)

// 성향 테스트 카운팅 조회
data class ChildQuestionCountDTO(
    @SerializedName("answer")
    val question: List<String>,
    @SerializedName("count")
    val questionAnswerCount: List<Int>
)

data class ParentQuestionCountDTO(
    @SerializedName("title")
    val parentQuestionTitle: String,
    @SerializedName("content")
    val childQuestions: ChildQuestionCountDTO
)

data class TravelTendencyQuestionCountRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ParentQuestionCountDTO
)

// 그룹 성향 테스트 결과 전체 조회
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

data class GroupTravelTendencyRes(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: GroupTravelTendencyDTO
)

// 성향테스트 결과 저장
data class StoreTravelTendencyReq(
    val tendencyScore: List<Int>,
    val tendencyChoice: List<Int>
)

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
)

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
    @GET("tendency/question")
    fun fetchTravelTendencyQuestion(): TravelTendencyQuestionRes

    @GET("tendency/question/{groupId}")
    fun fetchTravelTendencyQuestionCount(
        @Path("groupId") groupId: String
    ): TravelTendencyQuestionCountRes

    @GET("tendency/{groupId}")
    fun fetchGroupTravelTendency(
        @Path("groupId") groupId: String
    ): GroupTravelTendencyRes

    @POST("tendency/{groupId}")
    fun storeTravelTendency(
        @Body storeTravelTendencyReq: StoreTravelTendencyReq,
        @Path("groupId") groupId: String
    ): StoreTravelTendencyRes
}