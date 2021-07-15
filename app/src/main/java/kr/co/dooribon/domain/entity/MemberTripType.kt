package kr.co.dooribon.domain.entity

data class MemberTripType(
    val typeName: String,
    val typeKeyword: List<String>,
    val userName: String,
    val userImageUrl: String
)


/**
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
**/