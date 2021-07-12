package kr.co.dooribon.di

import kr.co.dooribon.api.remote.TravelAPI
import kr.co.dooribon.application.MainApplication.Companion.retrofitModule

class ApiModule {

    val travelApi by lazy {
        retrofitModule.createApi(TravelAPI::class)
    }

}