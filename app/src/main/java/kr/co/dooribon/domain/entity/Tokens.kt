package kr.co.dooribon.domain.entity

data class Tokens(
    val accessToken: String,
    val refreshToken: String,
    val jwtToken: String
)
