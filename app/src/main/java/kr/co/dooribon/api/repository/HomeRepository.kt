package kr.co.dooribon.api.repository

import kr.co.dooribon.api.remote.TravelAPI

class HomeRepository(
    private val travelAPI: TravelAPI
) {
    suspend fun fetchHomeDataTest() = travelAPI.fetchUserTravel()
}