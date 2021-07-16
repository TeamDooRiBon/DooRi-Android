package kr.co.dooribon.ui.newtrip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TravelDoneViewModel : ViewModel() {

    private val _inviteCode = MutableLiveData<String>()
    val inviteCode: LiveData<String>
        get() = _inviteCode

    fun initializeInviteCode(inviteCode : String){
        _inviteCode.value = inviteCode
    }
}