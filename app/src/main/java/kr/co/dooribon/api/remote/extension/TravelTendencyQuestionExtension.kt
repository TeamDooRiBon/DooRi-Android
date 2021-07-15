package kr.co.dooribon.api.remote.extension

import kr.co.dooribon.api.remote.*
import kr.co.dooribon.domain.entity.ParentTravelTendency
import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.UpComingTravel

fun List<TravelDTO>.asDomainUpComingTravel(): List<UpComingTravel> {
    return map {
        it.asDomainUpComingTravel()
    }
}

fun List<TravelDTO>.asDomainPreviousTravel(): List<PreviousTravel> {
    return map {
        it.asDomainPreviousTravel()
    }
}

fun ParentQuestionDTO.asDomainParentQuestion() : ParentTravelTendency {
    return ParentTravelTendency(
        parentQuestionTitle = this.parentQuestionTitle,
        childQuestions = this.childQuestions.asDomainChildQuestionList()
    )
}

fun ChildQuestionDTO.asDomainChildQuestion() : ParentTravelTendency.ChildTravelTendencyQuestion{
    return ParentTravelTendency.ChildTravelTendencyQuestion(
        childQuestionTitle = this.questionsTitle,
        childQuestionWeight = this.questionsWeight
    )
}

fun List<ChildQuestionDTO>.asDomainChildQuestionList() : List<ParentTravelTendency.ChildTravelTendencyQuestion>{
    return map {
        it.asDomainChildQuestion()
    }
}

fun List<ParentQuestionDTO>.asDomainParentQuestionList() : List<ParentTravelTendency> {
    return map {
        it.asDomainParentQuestion()
    }
}