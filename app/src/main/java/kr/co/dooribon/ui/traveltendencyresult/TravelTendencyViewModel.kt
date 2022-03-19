package kr.co.dooribon.ui.traveltendencyresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.dooribon.utils.debugSSong

class TravelTendencyViewModel : ViewModel() {

    private val _travelTendencyResultImageUrl = MutableLiveData<String>()
    val travelTendencyResultImageUrl: LiveData<String>
        get() = _travelTendencyResultImageUrl

    private val _travelTendencyResultName = MutableLiveData<String>()
    val travelTendencyResultName : LiveData<String>
        get() = _travelTendencyResultName

    private val _travelTendencyUserName = MutableLiveData<String>()
    val travelTendencyUserName : LiveData<String>
        get() = _travelTendencyUserName

    fun initializeTravelTendencyResultImageUrl(imageUrl: String) {
        _travelTendencyResultImageUrl.value = imageUrl
    }

    fun initializeTravelTendencyResultName(resultName : String){
        _travelTendencyResultName.value = resultName
    }

    fun initializeTravelTendencyResultUserName(userName : String){
        _travelTendencyUserName.value = userName
    }
}