package kr.co.dooribon.ui.existingtrip.tendency.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.GroupTravelTendencyDTO
import kr.co.dooribon.api.remote.StoreTravelTendencyDTO
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.utils.SingleLiveEvent
import kr.co.dooribon.utils.debugE

class MemberViewModel(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModel(), LifecycleObserver {

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

    // 성향 테스트 결과
    private val _travelTendencyResult = MutableLiveData<StoreTravelTendencyDTO>()
    val travelTendencyResult : LiveData<StoreTravelTendencyDTO>
        get() = _travelTendencyResult

    fun initializeMemberTendencyGroupId(groupId: String) {
        _memberTendencyGroupId.value = groupId
        debugE(_memberTendencyGroupId.value.toString())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchGroupTravelTendency() {
        viewModelScope.launch {
            runCatching {
                tripTendencyRepository.fetchGroupTravelTendency(_memberTendencyGroupId.value!!)
            }.onSuccess {
                if (it.data.otherTravelTendencyResult != null && it.data.otherTravelTendencyResult.isNotEmpty()) {// 다른 그룹의 성향 정보가 있는 경우
                    _isOtherTravelTendencyResult.value = false
                    _otherTravelTendencyResult.value = it.data.otherTravelTendencyResult
                } else {
                    _isOtherTravelTendencyResult.value = true
                    Log.d("otherEvent",_isOtherTravelTendencyResult.value.toString())
                    Log.d("other",_otherTravelTendencyResult.value.toString())
                }

                if (it.data.myTravelTendencyResult == null) {
                    _isMyTravelTendencyResult.value = false
                } else {// 나의 성향 정보가 있는 경우 , 지금 현재 나의 성향 정보가 없으므로 일단 냅둬
                    // 여기 부분 Domain으로 변환 x , 시간 부족함
                    _isMyTravelTendencyResult.value = true
                    _myTravelTendencyResult.value = it.data.myTravelTendencyResult
                    Log.d("mineEvent",_isMyTravelTendencyResult.value.toString())
                    Log.d("mine",_myTravelTendencyResult.value.toString())
                }
            }.onFailure {
                debugE(it)
            }
        }
    }

    fun initializeTravelTendencyResult(storeTravelTendencyDTO: StoreTravelTendencyDTO){
        _travelTendencyResult.value = storeTravelTendencyDTO
    }

}