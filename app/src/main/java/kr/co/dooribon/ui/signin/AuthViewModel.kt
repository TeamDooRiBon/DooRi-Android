package kr.co.dooribon.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.AuthDTO
import kr.co.dooribon.api.repository.AuthRepository
import kr.co.dooribon.mapper.TokenMapperImpl
import kr.co.dooribon.mapper.UserInfoMapperImpl
import kr.co.dooribon.utils.debugSSong

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    sealed class UIState {
        object Start : UIState()
        object Progress : UIState()
        object Complete : UIState()
        object Error : UIState()
    }

    private val _loading: MutableStateFlow<UIState> = MutableStateFlow(UIState.Start)
    val loading: StateFlow<UIState>
        get() = _loading

    private val _auth: MutableSharedFlow<AuthDTO> = MutableSharedFlow()
    val auth: SharedFlow<AuthDTO>
        get() = _auth

    val errorToastLiveData = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch {
            runCatching {
                authRepository.loginApi(
                    onStart = {
                        _loading.value = UIState.Progress
                    },
                    onComplete = {
                        _loading.value = UIState.Complete
                    },
                    onError = {
                        errorToastLiveData.postValue(it)
                        _loading.value = UIState.Error
                    }
                )
            }.onSuccess {

            }.onFailure {
                _loading.value = UIState.Error
                errorToastLiveData.postValue(it.toString())
            }
        }
    }

    fun loginKakaoAccount(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            runCatching {
                authRepository.loginKakaoAccount(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                    onStart = {
                        _loading.value = UIState.Progress
                    },
                    onComplete = {
                        _loading.value = UIState.Complete
                    },
                    onError = {
                        errorToastLiveData.postValue(it)
                        _loading.value = UIState.Error
                    }
                )
            }.onSuccess {
                debugSSong("onSuccess Invoke")
                authRepository.run {
                    saveTokens(TokenMapperImpl().toToken(it.data))
                    saveUserInfo(UserInfoMapperImpl().toUserInfo(it.data.user))
                }
                _loading.value = UIState.Complete
            }.onFailure {
                debugSSong(it.toString())
                _loading.value = UIState.Error
                errorToastLiveData.postValue(it.toString())
            }
        }
    }
}