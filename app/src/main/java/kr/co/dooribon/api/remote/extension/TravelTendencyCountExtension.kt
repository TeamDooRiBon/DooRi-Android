package kr.co.dooribon.api.remote.extension

import kr.co.dooribon.api.remote.ChildQuestionCountDTO
import kr.co.dooribon.api.remote.ParentQuestionCountDTO
import kr.co.dooribon.domain.entity.AnswerQuestion

/*
data class ParentQuestionCountDTO(
    @SerializedName("title")
    val parentQuestionTitle: String,
    @SerializedName("content")
    val childQuestions: List<ChildQuestionCountDTO>
)

data class ChildQuestionCountDTO(
    @SerializedName("answer")
    val question: String,
    @SerializedName("count")
    val questionAnswerCount: Int
)*/

/* data class AnswerQuestion(
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
} */
fun ChildQuestionCountDTO.asDomainChildQuestion(): AnswerQuestion.Question.ChildQuestion {
    return AnswerQuestion.Question.ChildQuestion(
        questionSubject = this.question,
        resultMemberNumber = this.questionAnswerCount
    )
}

fun ParentQuestionCountDTO.asDomainParentQuestion(): AnswerQuestion.Question {
    return AnswerQuestion.Question(
        questionTitle = this.parentQuestionTitle,
        questionSubject = this.childQuestions.asDomainChildQuestionList()
    )
}

fun List<ChildQuestionCountDTO>.asDomainChildQuestionList(): List<AnswerQuestion.Question.ChildQuestion> {
    return map {
        it.asDomainChildQuestion()
    }
}

fun List<ParentQuestionCountDTO>.asDomainParentQuestionList(): List<AnswerQuestion.Question> {
    return map {
        it.asDomainParentQuestion()
    }
}