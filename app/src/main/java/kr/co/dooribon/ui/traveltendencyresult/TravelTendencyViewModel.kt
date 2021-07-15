package kr.co.dooribon.ui.traveltendencyresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.dooribon.utils.debugSSong

class TravelTendencyViewModel : ViewModel() {

    private val _travelTendencyResultImageUrl = MutableLiveData<String>()
    val travelTendencyResultImageUrl : LiveData<String>
        get() = _travelTendencyResultImageUrl

    fun initializeTravelTendencyResultImageUrl(imageUrl : String){
        _travelTendencyResultImageUrl.value = imageUrl
        debugSSong(_travelTendencyResultImageUrl.value)
    }
}