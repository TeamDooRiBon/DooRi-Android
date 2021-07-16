package kr.co.dooribon.ui.newtrip.join.viewmodel

import android.text.TextUtils
import androidx.lifecycle.*
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
        debugSSong(_code1Text.value)
    }

    fun updateCode2Text(code2Text: String) {
        _code2Text.value = code2Text
        debugSSong(_code2Text.value)
    }

    fun updateCode3Text(code3Text: String) {
        _code3Text.value = code3Text
        debugSSong(_code3Text.value)
    }

    fun updateCode4Text(code4Text: String) {
        _code4Text.value = code4Text
        debugSSong(_code4Text.value)
    }

    fun updateCode5Text(code5Text: String) {
        _code5Text.value = code5Text
        debugSSong(_code5Text.value)
    }

    fun updateCode6Text(code6Text: String) {
        _code6Text.value = code6Text
        debugSSong(_code6Text.value)
    }

    fun updateSingleLiveEvent(value : Boolean){
        singleLiveEvent.value = value
    }
}