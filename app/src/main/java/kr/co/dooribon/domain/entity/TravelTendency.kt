package kr.co.dooribon.domain.entity

data class ParentTravelTendency( // 질문에 대한 가중치를 받을 배열이 필요함
    val parentQuestionTitle: String, // title
    val childQuestions: List<ChildTravelTendencyQuestion> // ChildQuestion
) {
    data class ChildTravelTendencyQuestion( // answer
        val childQuestionTitle: String,
        val childQuestionWeight: List<Int>
    )
}