package kr.co.dooribon.di

import kr.co.dooribon.api.remote.ScheduleAPI
import kr.co.dooribon.api.remote.TravelAPI
import kr.co.dooribon.application.MainApplication.Companion.retrofitModule

class ApiModule {

    val travelApi by lazy {
        retrofitModule.createApi(TravelAPI::class)
    }

    val scheduleApi by lazy {
        retrofitModule.createApi(ScheduleAPI::class)
    }

}