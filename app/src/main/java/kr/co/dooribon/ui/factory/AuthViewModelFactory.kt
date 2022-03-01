package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.api.repository.AuthRepository
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.DetailViewModel
import kr.co.dooribon.ui.signin.AuthViewModel

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(AuthViewModel::class.java)) { "unknown class name" }
        return AuthViewModel(authRepository) as T
    }
}