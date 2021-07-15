package kr.co.dooribon.ui.existingtrip.tendency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.StoreTravelTendencyDTO
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.utils.SingleLiveEvent
import kr.co.dooribon.utils.debugE

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