package kr.co.dooribon.ui.existingtrip.viewmodel

import androidx.lifecycle.ViewModel

class ExistingTripViewModel : ViewModel() {

    private var _groupId = ""

    fun setGroupId(groupId : String){
        _groupId = groupId
    }

    fun getGroupId() = _groupId
}