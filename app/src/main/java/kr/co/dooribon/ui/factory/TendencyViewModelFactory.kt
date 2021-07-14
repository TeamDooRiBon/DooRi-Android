package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.TendencyViewModel

class TendencyViewModelFactory(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(
            modelClass.isAssignableFrom(
                TendencyViewModel::class.java
            )
        ) { "unknown class name" }
        return TendencyViewModel(tripTendencyRepository) as T
    }
}