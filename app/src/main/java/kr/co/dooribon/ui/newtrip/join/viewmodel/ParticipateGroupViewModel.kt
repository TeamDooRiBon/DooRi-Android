package kr.co.dooribon.ui.newtrip.join.viewmodel

import android.text.TextUtils
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kr.co.dooribon.api.repository.ParticipateGroupRepository
import kr.co.dooribon.utils.SingleLiveEvent
import kr.co.dooribon.utils.debugSSong

class ParticipateGroupViewModel(
    private val participateGroupRepository: ParticipateGroupRepository
) : ViewModel() {

    private val _code1Text = MutableLiveData<String>()
    val code1Text : LiveData<String>
        get() = _code1Text

    private val _code2Text = MutableLiveData<String>()
    val code2Text : LiveData<String>
        get() = _code2Text

    private val _code3Text = MutableLiveData<String>()
    val code3Text : LiveData<String>
        get() = _code3Text

    private val _code4Text = MutableLiveData<String>()
    val code4Text : LiveData<String>
        get() = _code4Text

    private val _code5Text = MutableLiveData<String>()
    val code5Text : LiveData<String>
        get() = _code5Text

    private val _code6Text = MutableLiveData<String>()
    val code6Text : LiveData<String>
        get() = _code6Text

    val singleLiveEvent = SingleLiveEvent<Boolean>()

    fun updateCode1Text(code1Text: String) {
        _code1Text.value = code1Text
    }

    fun updateCode2Text(code2Text: String) {
        _code2Text.value = code2Text
    }

    fun updateCode3Text(code3Text: String) {
        _code3Text.value = code3Text
    }

    fun updateCode4Text(code4Text: String) {
        _code4Text.value = code4Text
    }

    fun updateCode5Text(code5Text: String) {
        _code5Text.value = code5Text
    }

    fun updateCode6Text(code6Text: String) {
        _code6Text.value = code6Text
    }

    fun updateSingleLiveEvent(value : Boolean){
        singleLiveEvent.value = value
    }

    fun makeInviteCode() : String{
        val inviteCode = StringBuilder()
        inviteCode.append(_code1Text.value)
        inviteCode.append(_code2Text.value)
        inviteCode.append(_code3Text.value)
        inviteCode.append(_code4Text.value)
        inviteCode.append(_code5Text.value)
        inviteCode.append(_code6Text.value)
        debugSSong(inviteCode.toString())

        return inviteCode.toString()
    }
}