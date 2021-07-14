package kr.co.dooribon.ui.triptendency.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.asDomainParentQuestionList
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.domain.entity.ParentTravelTendency
import kr.co.dooribon.utils.SingleLiveEvent
import kr.co.dooribon.utils.debugE

class TripTendencyViewModel(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModel() {

    // 현재 몇번째 질문이 보여지고 있는 지 알려주는 변수
    private val _questionPosition = MutableLiveData(0)
    val questionPosition: LiveData<Int>
        get() = _questionPosition

    // 질문들에서 유저가 선택한 포지션이 몇번인지를 담아두는 변수
    private val _lastQuestionSelectedPosition =
        MutableLiveData(MutableList(MAX_QUESTION_COUNT) { _ -> -1 })
    val lastQuestionSelectedPosition: LiveData<MutableList<Int>>
        get() = _lastQuestionSelectedPosition

    // 성향 테스트 질문들
    private val _travelTendencyQuestions = MutableLiveData<List<ParentTravelTendency>>()
    val travelTendencyQuestions: LiveData<List<ParentTravelTendency>>
        get() = _travelTendencyQuestions

    // 각 질문마다 선택한 답의 가중치의 합을 보관해 놓는 리스트
    private val _questionResultList = MutableList(10){_ -> -1}
    val questionResultList : List<Int>
        get() = _questionResultList

    val toastEventLiveData = SingleLiveEvent<String>()

    init {
        _questionPosition.value = 0
        viewModelScope.launch {
            runCatching {
                tripTendencyRepository.fetchTravelTendencyQuestions()
            }.onSuccess {
                _travelTendencyQuestions.value = it.data.asDomainParentQuestionList()
                debugE(it.toString())
            }.onFailure {
                debugE(it.toString())
            }
        }
    }

    fun nextPage() {
        if (_questionPosition.value!! < MAX_QUESTION_COUNT - 1) {
            if (_lastQuestionSelectedPosition.value!![_questionPosition.value!!] != -1) {
                _questionPosition.value = _questionPosition.value?.plus(1)
            } else {
                toastEventLiveData.value = "질문을 클릭해주세요"
            }
        }
    }

    fun previousPage() {
        if (_questionPosition.value!! > 0) {
            if (_lastQuestionSelectedPosition.value!![_questionPosition.value!!] != -1) {
                _questionPosition.value = _questionPosition.value?.minus(1)
            } else {
                toastEventLiveData.value = "질문을 클릭해주세요"
            }
        }
    }

    fun selectQuestion(selectedPosition: Int) {
        _lastQuestionSelectedPosition.value!![_questionPosition.value!!] = selectedPosition
    }

    fun getQuestionPosition() = _questionPosition.value

    fun getLastQuestionSelectedPosition() = _lastQuestionSelectedPosition.value

    companion object {
        private const val MAX_QUESTION_COUNT = 10
    }
}