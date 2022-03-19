package kr.co.dooribon.mapper

import kr.co.dooribon.api.remote.UserDTO
import kr.co.dooribon.domain.entity.UserInfo

class UserInfoMapperImpl : UserInfoMapper {
    override fun toUserInfo(userDTO: UserDTO): UserInfo {
        return UserInfo(userDTO.id, userDTO.name, userDTO.email, userDTO.profileImageUrl)
    }
}