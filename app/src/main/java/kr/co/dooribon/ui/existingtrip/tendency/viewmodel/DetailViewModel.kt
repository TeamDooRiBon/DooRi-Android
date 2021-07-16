package kr.co.dooribon.ui.existingtrip.tendency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.extension.asDomainParentQuestionList
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.domain.entity.AnswerQuestion
import kr.co.dooribon.utils.debugE

class DetailViewModel(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModel() {

    private val _memberTendencyGroupId = MutableLiveData<String>()
    val memberTendencyGroupId: LiveData<String>
        get() = _memberTendencyGroupId

    private val _membersTravelTendencyResult = MutableLiveData<List<AnswerQuestion.Question>>()
    val membersTravelTendencyResult: LiveData<List<AnswerQuestion.Question>>
        get() = _membersTravelTendencyResult

    fun initializeMemberTendencyGroupId(groupId: String) {
        _memberTendencyGroupId.value = groupId
        debugE(_memberTendencyGroupId.value.toString())
    }

    fun fetchGroupUserTravelTendencyCount() = viewModelScope.launch {
        runCatching {
            tripTendencyRepository.fetchTravelTendencyQuestionCount(_memberTendencyGroupId.value!!)
        }.onSuccess {
            _membersTravelTendencyResult.value = it.data.asDomainParentQuestionList()
        }.onFailure {
            debugE(it)
        }
    }
}