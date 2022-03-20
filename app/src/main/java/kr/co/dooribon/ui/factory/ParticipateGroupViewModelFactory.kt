package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.api.repository.ParticipateGroupRepository
import kr.co.dooribon.ui.newtrip.join.viewmodel.ParticipateGroupViewModel

class ParticipateGroupViewModelFactory(
    private val participateGroupRepository: ParticipateGroupRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(ParticipateGroupViewModel::class.java)) { "unknown class name" }
        return ParticipateGroupViewModel(participateGroupRepository) as T
    }
}