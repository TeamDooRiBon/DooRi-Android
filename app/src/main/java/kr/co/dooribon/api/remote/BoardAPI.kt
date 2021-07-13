package kr.co.dooribon.api.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.*

// boardTag 는 board에 가보면 tab이 4개가 있는데 그중 몇번째인지를 물어보는 거같다.
// 각각 여행 목표 = goal , 꼭 알아줘 = know , 역할 분담 = role , 체크리스트 = check로 해서 값을 넣어주면 될거 같습니다.

data class BoardContentDTO(
    @SerializedName("_id")
    val boardId : String,
    @SerializedName("name")
    val editUserName : String,
    @SerializedName("content")
    val boardContent : String
)

// 여행 보드 추가
data class CreateTravelBoardReq(
    @SerializedName("content")
    val travelBoardContent : String
)

data class CreateTravelBoardRes(
    val editTravelBoardRes : BaseResponse<BoardContentDTO>
)

// 여행 보드 조회
data class InquireTravelBoardRes(
    val inquireTravelBoardRes : BaseResponse<BoardContentDTO>
)

// 여행 보드 수정
data class EditTravelBoardReq(
    @SerializedName("content")
    val boardContent : String
)

data class EditTravelBoardRes(
    val editTravelBoardRes : BaseResponse<BoardContentDTO>
)

// 여행 보드 삭제
data class DeleteTravelBoardRes(
    val deleteTravelBoardRes : BaseResponse<BoardContentDTO>
)
interface BoardAPI{
    // 여행 보드 추가 뷰
    @POST("board/{groupId}/{tag}")
    fun createTravelBoard(
        @Path("groupId") groupId : String,
        @Path("tag") tag : String,
        @Body createTravelBoardReq: CreateTravelBoardReq
    ) : CreateTravelBoardRes

    // 태그별 여행 보드 조회 뷰
    @GET("board/{groupId}/{tag}")
    fun inquireTravelBoard(
        @Path("groupId") groupId : String,
        @Path("tag") tag : String
    ) : InquireTravelBoardRes

    // 여행 보드 수정 뷰 , 태그별 여행 보드 조회 뷰
    @PATCH("board/{groupId}/{tag}/{boardId}")
    fun editTravelBoard(
        @Body editTravelBoardReq : EditTravelBoardReq,
        @Path("groupId") groupId : String,
        @Path("tag") tag : String,
        @Path("boardId") boardId : String
    ) : EditTravelBoardRes

    // 태그별 여행 보드 조회 뷰에서 삭제 할 때
    @DELETE("board/{groupId}/{tag}/{boardId}")
    fun deleteTravelBoard(
        @Path("groupId") groupId : String,
        @Path("tag") tag : String,
        @Path("boardId") boardId : String
    ) : DeleteTravelBoardRes
}