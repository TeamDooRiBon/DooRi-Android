package kr.co.dooribon.ui.triptendency.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.dooribon.api.remote.StoreTravelTendencyDTO
import kr.co.dooribon.api.remote.StoreTravelTendencyReq
import kr.co.dooribon.api.remote.extension.asDomainParentQuestionList
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.domain.entity.ParentTravelTendency
import kr.co.dooribon.utils.SingleLiveEvent
import kr.co.dooribon.utils.debugE

class TripTendencyViewModel(
    private val tripTendencyRepository: TripTendencyRepository
) : ViewModel() {

    private val _groupId = MutableLiveData<String>()
    val groupId: LiveData<String>
        get() = _groupId

    // 현재 몇번째 질문이 보여지고 있는 지 알려주는 변수
    private val _questionPosition = MutableLiveData(0)
    val questionPosition: LiveData<Int>
        get() = _questionPosition

    // 질문들에서 유저가 선택한 포지션이 몇번인지를 담아두는 변수
    private val _lastQuestionSelectedPosition =
        MutableLiveData(MutableList(MAX_QUESTION_COUNT) { _ -> -1 })
    val lastQuestionSelectedPosition: LiveData<MutableList<Int>>
        get() = _lastQuestionSelectedPosition

    // 서버에 보낼 때 포지션 1부터 시작하는 걸 해줄 변수
    private val _selectedPositionForServer =
        MutableLiveData(MutableList(MAX_QUESTION_COUNT) { _ -> -1 })
    val selectedPositionForServer: LiveData<MutableList<Int>>
        get() = _selectedPositionForServer

    // 성향 테스트 질문들
    private val _travelTendencyQuestions = MutableLiveData<List<ParentTravelTendency>>()
    val travelTendencyQuestions: LiveData<List<ParentTravelTendency>>
        get() = _travelTendencyQuestions

    // 각 질문마다 선택한 답의 가중치의 합을 보관해 놓는 리스트
    private val _questionResultList = MutableList(10) { mutableListOf(10) }
    val questionResultList: List<List<Int>>
        get() = _questionResultList

    // 각 질문 별 가중치들의 합 리스트
    private val _questionWeightResultList = MutableList(MAX_QUESTION_TENDENCY_COUNT) { _ -> 0 }
    val questionWeightResultList: List<Int>
        get() = _questionWeightResultList

    // 성향 테스트 결과를 받을 데이터
    private val _travelTendencyResult = MutableLiveData<StoreTravelTendencyDTO>()
    val travelTendencyResult: LiveData<StoreTravelTendencyDTO>
        get() = _travelTendencyResult

    // 질문을 선택해달라고 하는 LiveData
    val toastEventLiveData = SingleLiveEvent<String>()

    init {
        _questionPosition.value = 0
        viewModelScope.launch {
            runCatching {
                tripTendencyRepository.fetchTravelTendencyQuestions()
            }.onSuccess {
                _travelTendencyQuestions.value = it.data.asDomainParentQuestionList()
            }.onFailure {
                debugE(it.toString())
            }
        }
    }

    fun initializeGroupId(groupId: String) {
        _groupId.value = groupId
    }

    fun nextPage() {
        if (_questionPosition.value!! < MAX_QUESTION_COUNT) {
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
        // questionList에 넣어놓는 로직
        _questionResultList[_questionPosition.value!!].clear()
        _questionResultList[_questionPosition.value!!].addAll(
            _travelTendencyQuestions.value?.get(
                _questionPosition.value!!
            )!!.childQuestions[selectedPosition].childQuestionWeight
        )
    }

    fun selectQuestionForServer(selectedPosition: Int) {
        _selectedPositionForServer.value!![_questionPosition.value!!] = selectedPosition + 1
    }

    fun calculateQuestionWeight() {
        _questionResultList.forEach {
            for (i in 0 until it.size) {
                _questionWeightResultList[i] += it[i]
            }
        }
    }

    fun storeMyTravelTendency() =
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _groupId.value?.let {
                    tripTendencyRepository.storeTravelTendency(
                        StoreTravelTendencyReq(
                            _questionWeightResultList,
                            _selectedPositionForServer.value!!
                        ),
                        it
                    )
                }
            }.onSuccess {
                _travelTendencyResult.postValue(it?.data)
            }.onFailure {
                debugE(it)
            }
        }


    companion object {
        private const val MAX_QUESTION_COUNT = 10
        private const val MAX_QUESTION_TENDENCY_COUNT = 8
    }
}