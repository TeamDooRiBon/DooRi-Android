package kr.co.dooribon.ui.existingtrip.tendency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.GroupTravelTendencyDTO
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

    private val _myTravelTendencyResult = MutableLiveData<GroupTravelTendencyDTO>()
    val myTravelTendencyResult: LiveData<GroupTravelTendencyDTO>
        get() = _myTravelTendencyResult

    private val _otherTravelTendencyResult = MutableLiveData<List<GroupTravelTendencyDTO>>()
    val otherTravelTendencyResult : LiveData<List<GroupTravelTendencyDTO>>
        get() = _otherTravelTendencyResult

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
                } else {// 나의 성향 정보가 있는 경우 , 지금 현재 나의 성향 정보가 없으므로 일단 냅둬
                    // 여기 부분 Domain으로 변환 x , 시간 부족함
                    _isMyTravelTendencyResult.value = true
                    _myTravelTendencyResult.value = it.data.myTravelTendencyResult
                }
                if (it.data.otherTravelTendencyResult == null) {
                    _isOtherTravelTendencyResult.value = false
                } else {// 다른 그룹의 성향 정보가 있는 경우
                    // 여기 부분 Domain으로 변환 x , 시간 부족함
                    _isOtherTravelTendencyResult.value = true
                    _otherTravelTendencyResult.value = it.data.otherTravelTendencyResult
                }
                debugE(it)
            }.onFailure {
                debugE(it)
            }
        }
    }

}