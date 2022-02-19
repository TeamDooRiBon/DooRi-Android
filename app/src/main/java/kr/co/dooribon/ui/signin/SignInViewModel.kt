package kr.co.dooribon.ui.signin

import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    sealed class Event {
        object LoginFailed : Event()
        data class Exist(val test : Int) : Event()
        object NotExist : Event()
        object Loading : Event()
    }
}