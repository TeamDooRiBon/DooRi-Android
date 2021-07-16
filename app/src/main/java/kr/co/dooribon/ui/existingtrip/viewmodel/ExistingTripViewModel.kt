package kr.co.dooribon.ui.existingtrip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.Travel
import kr.co.dooribon.domain.entity.UpComingTravel

class ExistingTripViewModel : ViewModel() {

    private var _groupId = ""

    private val _homeProceedingTravelContents = MutableLiveData<Travel>()
    val homeProceedingTravelContents: LiveData<Travel>
        get() = _homeProceedingTravelContents

    private val _homeUpComingTravelContents = MutableLiveData<UpComingTravel>()
    val homeUpComingTravelContents: LiveData<UpComingTravel>
        get() = _homeUpComingTravelContents

    private val _homePreviousTravelContents = MutableLiveData<PreviousTravel>()
    val homePreviousTravelContents: LiveData<PreviousTravel>
        get() = _homePreviousTravelContents

    fun initializeHomeProceedingTravel(homeProceedingTravel: Travel) {
        _homeProceedingTravelContents.value = homeProceedingTravel
    }

    fun initializeUpComingTravel(upComingTravel: UpComingTravel) {
        _homeUpComingTravelContents.value = upComingTravel
    }

    fun initializePreviousTravel(previousTravel: PreviousTravel) {
        _homePreviousTravelContents.value = previousTravel
    }

    fun setGroupId(groupId: String) {
        _groupId = groupId
    }

    fun getGroupId() = _groupId
}