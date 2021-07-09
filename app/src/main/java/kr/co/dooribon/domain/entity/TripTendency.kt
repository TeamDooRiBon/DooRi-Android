package kr.co.dooribon.domain.entity

data class TripTendency( // 질문에 대한 가중치를 받을 배열이 필요함
    val questionNumber: Int,
    val questionTitle: String, // title
    val questionList: List<TripTendencyQuestion>
) {
    data class TripTendencyQuestion( // answer
        val problemNumber: Int,
        val problemTitle: String
    )
}
