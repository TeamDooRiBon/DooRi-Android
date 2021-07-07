package kr.co.dooribon.domain.entity

class ExpandableAnswerQuestion {
    lateinit var questionParent: AnswerQuestion.Question
    var type: Int
    lateinit var questionChild: AnswerQuestion.Question.ChildQuestion
    var isExpanded: Boolean
    private var isCloseShown: Boolean

    constructor(
        type: Int,
        questionParent: AnswerQuestion.Question,
        isExpanded: Boolean = false,
        isCloseShown: Boolean = false
    ) {
        this.type = type
        this.questionParent = questionParent
        this.isExpanded = isExpanded
        this.isCloseShown = isCloseShown
    }

    constructor(
        type: Int,
        questionChild: AnswerQuestion.Question.ChildQuestion,
        isExpanded: Boolean = false,
        isCloseShown: Boolean = false
    ) {
        this.type = type
        this.questionChild = questionChild
        this.isExpanded = isExpanded
        this.isCloseShown = isCloseShown
    }

    companion object {
        const val PARENT = 1
        const val CHILD = 2
    }
}