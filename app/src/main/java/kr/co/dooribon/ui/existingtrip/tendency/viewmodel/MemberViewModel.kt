package kr.co.dooribon.ui.existingtrip.tendency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.utils.SingleLiveEvent
import kr.co.dooribon.utils.debugE

class MemberViewModel(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModel() {

    private val _memberTendencyGroupId = MutableLiveData<String>()
    val memberTendencyGroupId: LiveData<String>
        get() = _memberTendencyGroupId

    private val _isMyTravelTendencyResult = SingleLiveEvent<Boolean>()
    val isMyTravelTendencyResult: SingleLiveEvent<Boolean>
        get() = _isMyTravelTendencyResult

    private val _isOtherTravelTendencyResult = SingleLiveEvent<Boolean>()
    val isOtherTravelTendencyResult: SingleLiveEvent<Boolean>
        get() = _isOtherTravelTendencyResult

    fun initializeMemberTendencyGroupId(groupId: String) {
        _memberTendencyGroupId.value = groupId
        debugE(_memberTendencyGroupId.value.toString())
    }

    fun fetchGroupTravelTendency() {
        viewModelScope.launch {
            runCatching {
                tripTendencyRepository.fetchGroupTravelTendency(_memberTendencyGroupId.value!!)
            }.onSuccess {
                if (it.data.myTravelTendencyResult == null) {
                    _isMyTravelTendencyResult.value = false
                } else {
                    _isMyTravelTendencyResult.value = true
                    // 나의 성향 정보가 있는 경우
                }
                if(it.data.otherTravelTendencyResult == null){
                    _isOtherTravelTendencyResult.value = false
                } else {
                    // 다른 그룹의 성향 정보가 있는 경우
                    _isOtherTravelTendencyResult.value = true
                }
                debugE(_isOtherTravelTendencyResult.value)
                debugE(it)
            }.onFailure {
                debugE(it)
            }
        }
    }

}