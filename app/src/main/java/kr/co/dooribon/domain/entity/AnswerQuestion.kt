package kr.co.dooribon.domain.entity

data class AnswerQuestion(
    val questions : List<Question>
) {
    data class Question(
        val questionNumber : Int,
        val questionTitle : String,
        val questionSubject : List<ChildQuestion>
    ) {
        data class ChildQuestion(
            val questionNumber: Int,
            val questionSubject : String,
            val resultMemberNumber : Int
        )
    }
}