package kr.co.dooribon.ui.existingtrip.tendency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.dooribon.api.repository.TripTendencyRepository

class TendencyViewModel(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModel() {

    // GroupId
    private val _memberTendencyGroupId = MutableLiveData<String>()
    val memberTendencyGroupId: LiveData<String>
        get() = _memberTendencyGroupId

    fun initializeMemberTendencyGroupId(groupId: String) {
        _memberTendencyGroupId.value = groupId
    }

}