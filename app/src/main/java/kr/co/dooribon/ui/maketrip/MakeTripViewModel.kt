package kr.co.dooribon.ui.maketrip

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MakeTripViewModel : ViewModel() {
    private val _currentPagerPosition = MutableStateFlow(0)
    val currentPagerPosition get() = _currentPagerPosition.asStateFlow()

    fun addCurrentPosition() {
        _currentPagerPosition.value += 1
    }

    fun minusCurrentPosition() {
        _currentPagerPosition.value -= 1
    }
}