package kr.co.dooribon.mapper

import kr.co.dooribon.api.remote.AuthDTO
import kr.co.dooribon.domain.entity.Tokens

interface TokenMapper {
    fun toToken(authDTO : AuthDTO) : Tokens
}