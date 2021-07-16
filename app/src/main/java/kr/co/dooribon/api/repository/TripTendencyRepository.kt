package kr.co.dooribon.api.repository

import kr.co.dooribon.api.remote.StoreTravelTendencyReq
import kr.co.dooribon.api.remote.TendencyAPI

class TripTendencyRepository(
    private val tendencyAPI: TendencyAPI
) {
    suspend fun fetchTravelTendencyQuestions() = tendencyAPI.fetchTravelTendencyQuestion()

    suspend fun fetchTravelTendencyQuestionCount(groupId: String) =
        tendencyAPI.fetchTravelTendencyQuestionCount(groupId)

    suspend fun fetchGroupTravelTendency(groupId: String) =
        tendencyAPI.fetchGroupTravelTendency(groupId)

    suspend fun storeTravelTendency(
        storeTravelTendencyReq: StoreTravelTendencyReq,
        groupId: String
    ) = tendencyAPI.storeTravelTendency(storeTravelTendencyReq, groupId)
}