package kr.co.dooribon.api.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kr.co.dooribon.api.local.DooRiBonKeyStorage
import kr.co.dooribon.api.remote.AuthAPI
import kr.co.dooribon.api.remote.AuthDTO
import kr.co.dooribon.api.remote.BaseResponse
import kr.co.dooribon.api.remote.tokenReq
import kr.co.dooribon.application.MainApplication.Companion.sharedPreferenceModule
import kr.co.dooribon.domain.entity.Tokens
import kr.co.dooribon.domain.entity.UserInfo

class AuthRepository(
    private val authApi: AuthAPI,
    private val keyStorage: DooRiBonKeyStorage,
) {
    suspend fun loginKakaoAccount(
        accessToken : String,
        refreshToken : String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ): BaseResponse<AuthDTO> = withContext(Dispatchers.IO) {
        val response = authApi.login(tokenReq(accessToken,refreshToken))
        response
    }

    fun loginApi(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ): Flow<BaseResponse<AuthDTO>> = flow {
        val response = authApi.login(tokenReq(keyStorage.accessToken,keyStorage.refreshToken))
        emit(response)
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.catch {
        onError("Api Error")
    }.flowOn(Dispatchers.IO)

    fun saveTokens(tokens: Tokens) {
        keyStorage.apply {
            accessToken = tokens.accessToken
            refreshToken = tokens.refreshToken
            jwtToken = tokens.jwtToken
        }
    }

    fun saveUserInfo(userInfo: UserInfo) {
        keyStorage.apply {
            userId = userInfo.id
            userName = userInfo.name
            userEmail = userInfo.email
            profileImageUrl = userInfo.profileImageUrl
        }
    }
}