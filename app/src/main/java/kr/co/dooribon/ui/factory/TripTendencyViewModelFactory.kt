package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel

class TripTendencyViewModelFactory(

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(TripTendencyViewModel::class.java)) { "unknown class name" }
        return TripTendencyViewModel() as T
    }
}