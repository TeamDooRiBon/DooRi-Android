package kr.co.dooribon.ui.triptendency.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TripTendencyViewModel : ViewModel() {

    private val _questionPosition = MutableLiveData<Int>()
    val questionPosition: LiveData<Int>
        get() = _questionPosition

    // 질문들에서 유저가 선택한 포지션이 몇번인지를 담아두는 변수
    // 최대 질문수가 12개이므로 12개만
    private val _lastQuestionSelectedPosition =
        MutableLiveData<MutableList<Int>>(MutableList(MAX_QUESTION_COUNT) { _ -> 0 })
    val lastQuestionSelectedPosition: LiveData<MutableList<Int>>
        get() = _lastQuestionSelectedPosition

    init {
        _questionPosition.value = 0
    }

    fun nextPage(maxPageCount: Int) {
        if (_questionPosition.value!! < (maxPageCount - 1)) {
            _questionPosition.value = _questionPosition.value?.plus(1)
        }
    }

    fun previousPage() {
        if (_questionPosition.value!! > 0) {
            _questionPosition.value = _questionPosition.value?.minus(1)
        }
    }

    fun selectQuestion(selectedPosition: Int) {
        _lastQuestionSelectedPosition.value!![_questionPosition.value!!] = selectedPosition
    }

    fun getLastQuestionSelectedPosition(): Int =
        _lastQuestionSelectedPosition.value!![_questionPosition.value!!]

    companion object {
        private const val MAX_QUESTION_COUNT = 12
    }
}