package kr.co.dooribon.api.repository

import kr.co.dooribon.api.remote.TravelAPI
import kr.co.dooribon.api.remote.TravelImageAPI

class HomeRepository(
    private val travelAPI: TravelAPI,
    private val travelImageAPI : TravelImageAPI
) {
    suspend fun fetchHomeTravel() = travelAPI.fetchUserTravel()

    suspend fun fetchHomeProceedingTravelImage(groupId : String) = travelImageAPI.fetchHomeTravelImage(groupId)
}