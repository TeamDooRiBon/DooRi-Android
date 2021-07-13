package kr.co.dooribon.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.asDomainListTravel
import kr.co.dooribon.api.remote.asDomainPreviousTravel
import kr.co.dooribon.api.remote.asDomainTravel
import kr.co.dooribon.api.remote.asDomainUpComingTravel
import kr.co.dooribon.api.repository.HomeRepository
import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.Travel
import kr.co.dooribon.domain.entity.UpComingTravel
import kr.co.dooribon.utils.MockData
import kr.co.dooribon.utils.debugE

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeProceedingTravel = MutableLiveData<Travel>()
    val homeProceedingTravel: LiveData<Travel>
        get() = _homeProceedingTravel

    private val _homeUpComingTravel = MutableLiveData<List<UpComingTravel>>()
    val homeUpComingTravel: LiveData<List<UpComingTravel>>
        get() = _homeUpComingTravel

    private val _homePreviousTravel = MutableLiveData<List<PreviousTravel>>()
    val homePreviousTravel: LiveData<List<PreviousTravel>>
        get() = _homePreviousTravel

    private val _homeProceedingTravelImage = MutableLiveData<String>()
    val homeProceedingTravelImage: LiveData<String>
        get() = _homeProceedingTravelImage

    init {
        initializeHome()
    }

    private fun initializeHome() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                homeRepository.fetchHomeTravel()
            }.onSuccess { HomeTravelRes ->
                HomeTravelRes.data.forEach { HomeTravelDTO ->
                    when (HomeTravelDTO.travelType) {
                        "nowTravels" -> {
                            if (HomeTravelDTO.travelGroup.isNotEmpty()) _homeProceedingTravel.postValue(HomeTravelDTO.travelGroup[0].asDomainTravel())
                            else _homeProceedingTravel.postValue(MockData.provideHomeData())
                        }
                        "comeTravels" -> {
                            if (HomeTravelDTO.travelGroup.isNotEmpty()) _homeUpComingTravel.postValue(HomeTravelDTO.travelGroup.asDomainUpComingTravel())
                            else _homeUpComingTravel.postValue(MockData.provideUpComingData())
                        }
                        "endTravels" -> {
                            if (HomeTravelDTO.travelGroup.isNotEmpty()) _homePreviousTravel.postValue(HomeTravelDTO.travelGroup.asDomainPreviousTravel())
                            else _homePreviousTravel.postValue(MockData.providePreviousData())
                        }
                    }
                }
            }.onFailure {
                debugE(it.toString())
            }
        }
    }
}