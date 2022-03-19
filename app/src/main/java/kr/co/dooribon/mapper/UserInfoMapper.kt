package kr.co.dooribon.mapper

import kr.co.dooribon.api.remote.UserDTO
import kr.co.dooribon.domain.entity.UserInfo

interface UserInfoMapper {
    fun toUserInfo(userDTO : UserDTO) : UserInfo
}