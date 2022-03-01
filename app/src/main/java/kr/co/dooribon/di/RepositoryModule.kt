package kr.co.dooribon.di

import kr.co.dooribon.api.repository.AuthRepository
import kr.co.dooribon.api.repository.HomeRepository
import kr.co.dooribon.api.repository.ParticipateGroupRepository
import kr.co.dooribon.api.repository.TripTendencyRepository
import kr.co.dooribon.application.MainApplication.Companion.apiModule
import kr.co.dooribon.application.MainApplication.Companion.keyStorageModule
import kr.co.dooribon.application.MainApplication.Companion.sharedPreferenceModule

class RepositoryModule {

    val homeRepository by lazy {
        HomeRepository(apiModule.travelApi, apiModule.travelImageApi)
    }

    val tripTendencyRepository by lazy {
        TripTendencyRepository(apiModule.tendencyApi)
    }

    val participateGroupRepository by lazy {
        ParticipateGroupRepository(apiModule.travelApi)
    }

    val authRepository by lazy {
        AuthRepository(apiModule.authApi, keyStorageModule.dooRiBonKeyStorage)
    }
}