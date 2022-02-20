package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.api.repository.HomeRepository
import kr.co.dooribon.ui.home.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(HomeViewModel::class.java)) { "unknown class name" }
        return HomeViewModel(homeRepository) as T
    }
}