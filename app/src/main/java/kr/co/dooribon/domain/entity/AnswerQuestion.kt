package kr.co.dooribon.domain.entity

/**
 * 여행 성향의 답을 내놓은 것을 보여주는 Data 클래스
 */
data class AnswerQuestion(
    val questions: List<Question>
) {
    data class Question(
        val questionNumber: Int,
        val questionTitle: String,
        val questionSubject: List<ChildQuestion>
    ) {
        data class ChildQuestion(
            val questionNumber: Int,
            val questionSubject: String,
            val resultMemberNumber: Int
        )
    }
}