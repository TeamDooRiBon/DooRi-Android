package kr.co.dooribon.domain.entity

data class TripTendency(
    val questionNumber: Int,
    val questionTitle: String,
    val questionList: List<TripTendencyQuestion>
) {
    data class TripTendencyQuestion(
        val problemNumber: Int,
        val problemTitle: String
    )
}
