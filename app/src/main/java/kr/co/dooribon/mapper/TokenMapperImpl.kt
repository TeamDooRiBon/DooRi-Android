package kr.co.dooribon.mapper

import kr.co.dooribon.api.remote.AuthDTO
import kr.co.dooribon.domain.entity.Tokens

class TokenMapperImpl : TokenMapper {
    override fun toToken(authDTO: AuthDTO): Tokens {
        return Tokens(authDTO.accessToken,authDTO.refreshToken,authDTO.jwtToken)
    }
}