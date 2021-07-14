package kr.co.dooribon.di

import kr.co.dooribon.api.repository.HomeRepository
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.application.MainApplication.Companion.apiModule

class RepositoryModule {

    val homeRepository by lazy {
        HomeRepository(apiModule.travelApi, apiModule.travelImageApi)
    }
    
    val tripTendencyRepository by lazy {
        TripTendencyRepository(apiModule.tendencyApi)
    }
}