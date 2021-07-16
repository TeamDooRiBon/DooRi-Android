package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.DetailViewModel

class DetailViewModelFactory(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(DetailViewModel::class.java)) { "unknown class name" }
        return DetailViewModel(tripTendencyRepository) as T
    }
}