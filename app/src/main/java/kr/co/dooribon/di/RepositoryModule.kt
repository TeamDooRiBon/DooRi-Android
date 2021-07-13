package kr.co.dooribon.di

import kr.co.dooribon.api.repository.HomeRepository
import kr.co.dooribon.application.MainApplication.Companion.apiModule

class RepositoryModule {

    val homeRepository by lazy {
        HomeRepository(apiModule.travelApi, apiModule.travelImageAPI)
    }
}