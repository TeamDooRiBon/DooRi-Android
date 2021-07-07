package kr.co.dooribon.domain.entity

data class MemberTripType(
    val typeName: String,
    val typeKeyword: List<String>,
    val userName: String,
    val userImageUrl: String
)