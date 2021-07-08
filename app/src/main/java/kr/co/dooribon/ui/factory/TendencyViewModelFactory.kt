package kr.co.dooribon.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.TendencyViewModel

class TendencyViewModelFactory(

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(
            TendencyViewModel::class.java)) {"unknown class name"}
        return TendencyViewModel() as T
    }
}