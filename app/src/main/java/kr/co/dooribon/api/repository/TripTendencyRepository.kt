package kr.co.dooribon.api.repository

import kr.co.dooribon.api.remote.TendencyAPI

class TripTendencyRepository(
    private val tendencyAPI: TendencyAPI
) {
    suspend fun fetchTravelTendencyQuestions() = tendencyAPI.fetchTravelTendencyQuestion()
}