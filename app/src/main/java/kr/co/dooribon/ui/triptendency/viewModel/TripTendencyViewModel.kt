package kr.co.dooribon.ui.triptendency.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.dooribon.domain.entity.TripTendency
import kr.co.dooribon.utils.SingleLiveEvent

class TripTendencyViewModel : ViewModel() {

    private val _questionPosition = MutableLiveData<Int>()
    val questionPosition: LiveData<Int>
        get() = _questionPosition

    // 질문들에서 유저가 선택한 포지션이 몇번인지를 담아두는 변수
    // 최대 질문수가 10개이므로 10개만
    private val _lastQuestionSelectedPosition =
        MutableLiveData(MutableList(MAX_QUESTION_COUNT) { _ -> -1 })
    val lastQuestionSelectedPosition: LiveData<MutableList<Int>>
        get() = _lastQuestionSelectedPosition

    val toastEventLiveData = SingleLiveEvent<String>()

    init {
        _questionPosition.value = 0
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

    // dummy 함수
    fun getDummy(): List<TripTendency> =
        listOf(
            TripTendency(
                1, "송훈기 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "안괜찮아"),
                    TripTendency.TripTendencyQuestion(2, "괜찮아"),
                    TripTendency.TripTendencyQuestion(3, "공감 못함"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 0호")
                )
            ),
            TripTendency(
                2, "조예진 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "호기심 대마왕"),
                    TripTendency.TripTendencyQuestion(2, "아니 근데"),
                    TripTendency.TripTendencyQuestion(3, "킹받게 함"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 1호...")
                )
            ),
            TripTendency(
                3, "이원중 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "방금 선그어서 멋져보임"),
                    TripTendency.TripTendencyQuestion(3, "가끔 킹받게 함"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 2호")
                )
            ),
            TripTendency(
                4, "김태현 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "아요대장"),
                    TripTendency.TripTendencyQuestion(3, "노는거 좋아함"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 3호")
                )
            ),
            TripTendency(
                5, "한상진 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "캘린더 장인"),
                    TripTendency.TripTendencyQuestion(3, "합숙중임"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 4호")
                )
            ),
            TripTendency(
                6, "이민재 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "야구장인"),
                    TripTendency.TripTendencyQuestion(3, "홈화면 중인걸로 암"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 5호")
                )
            ),
            TripTendency(
                7, "박유진 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "피엠임"),
                    TripTendency.TripTendencyQuestion(3, "기획대장"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 6호")
                )
            ),
            TripTendency(
                8, "김민영 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "디자인 잘함"),
                    TripTendency.TripTendencyQuestion(3, "내가 마니또 였음"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 7호")
                )
            ),
            TripTendency(
                9, "유지인 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "디자인 잘함"),
                    TripTendency.TripTendencyQuestion(3, "낯가린다고 했는데 잘 안가리는거 같음"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 8호")
                )
            ),
            TripTendency(
                10, "김인우 이대로 괜찮은가", listOf(
                    TripTendency.TripTendencyQuestion(1, "개잘함"),
                    TripTendency.TripTendencyQuestion(2, "디자인 잘함"),
                    TripTendency.TripTendencyQuestion(3, "말 속에 뼈가 있을거같음"),
                    TripTendency.TripTendencyQuestion(4, "처치대상 9호")
                )
            )
        )

    fun getQuestionPosition() = _questionPosition.value

    fun getLastQuestionSelectedPosition() = _lastQuestionSelectedPosition.value

    companion object {
        private const val MAX_QUESTION_COUNT = 10
    }
}